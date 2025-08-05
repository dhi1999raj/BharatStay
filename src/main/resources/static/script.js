document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.getElementById('clock-room-search-form'); // Assuming your form has this ID
    const searchResultsDiv = document.getElementById('search-results');

    if (searchForm) {
        searchForm.addEventListener('submit', async (event) => {
            event.preventDefault();

            const location = document.getElementById('location').value; // Assuming input has this ID
            const checkin = document.getElementById('check-in-date').value; // Assuming input has this ID
            const checkout = document.getElementById('check-out-date').value; // Assuming input has this ID

            try {
                const response = await fetch(`/search?location=${encodeURIComponent(location)}&checkin=${encodeURIComponent(checkin)}&checkout=${encodeURIComponent(checkout)}`);

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                console.log(data); // Log the data to the console for now

                // Clear previous results
                searchResultsDiv.innerHTML = '';

                if (data.length > 0) {
                    const resultsList = document.createElement('ul');
                    data.forEach(room => {
                        const listItem = document.createElement('li');
                        listItem.innerHTML = `
                            <h3>${room.name}</h3>
                            <p>Location: ${room.location}</p>
                            <p>Price: $${room.price.toFixed(2)}</p>
                            <button class="book-button" data-room-id="${room.id}">Book</button>
                        `; // Assuming ClockRoom object has name, location, price, and id
                        resultsList.appendChild(listItem);
                    });
                    searchResultsDiv.appendChild(resultsList);
                } else {
                    searchResultsDiv.innerHTML = '<p>No clock rooms found for your criteria.</p>';
                }


            } catch (error) {
                console.error('Error fetching search results:', error);
                searchResultsDiv.innerHTML = `<p>Error searching for clock rooms. Please try again.</p>`;
            }
        });
    } else {
        console.error("Search form not found with ID 'clock-room-search-form'");
    }
});