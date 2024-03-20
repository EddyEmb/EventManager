async function fetchData(url) {
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return await response.json();
  } catch (error) {
    console.error('Error fetching data:', error);
    throw error;
  }
}

async function fetchDataAndRenderRow(domain, path, containerId) {
  try {
    const url = `${domain}${path}`;
    const data = await fetchData(url);

    console.log('Fetched data:', data); // Log fetched data for debugging

    if (Array.isArray(data)) {
      const groupedEvents = groupEventsByCommunity(data);
      renderGroupedEvents(groupedEvents, containerId);
    } else if (typeof data === 'object') {
      renderSingleEvent(data, containerId);
    } else {
      console.error('Invalid data format:', data);
    }
  } catch (error) {
    console.error('Error:', error);
  }
}
function renderSingleEvent(event, containerId) {
  const container = document.getElementById(containerId);
  container.innerHTML = `
    <table border="1">
      <tr><th>ID</th><th>Title</th><th>Description</th><th>Date</th><th>Status</th><th>Start Time</th><th>End Time</th><th>Event Limit</th><th>Community</th></tr>
      <tr>
        <td>${event.id}</td>
        <td>${event.title}</td>
        <td>${event.description}</td>
        <td>${event.date}</td>
        <td>${event.status}</td>
        <td>${event.startTime}</td>
        <td>${event.endTime}</td>
        <td>${event.eventlimit}</td>
        <td>${event.communityResponse.name}</td>
      </tr>
    </table>`;
}

function groupEventsByCommunity(events) {
  const groupedEvents = {};
  events.forEach(event => {
    const communityName = event.communityResponse.name;
    if (!groupedEvents[communityName]) {
      groupedEvents[communityName] = [];
    }
    groupedEvents[communityName].push(event);
  });
  return groupedEvents;
}

function renderGroupedEvents(groupedEvents, containerId) {
  const container = document.getElementById(containerId);
  container.innerHTML = '';

  for (const communityName in groupedEvents) {
    if (groupedEvents.hasOwnProperty(communityName)) {
      const events = groupedEvents[communityName];
      const headerHTML = `<tr><th colspan="9">${communityName}</th></tr>`;
      const bodyHTML = events.map(event => `
        <tr>
          <td>${event.id}</td>
          <td>${event.title}</td>
          <td>${event.description}</td>
          <td>${event.date}</td>
          <td>${event.status}</td>
          <td>${event.startTime}</td>
          <td>${event.endTime}</td>
          <td>${event.eventlimit}</td>
          <td>${event.communityResponse.name}</td>
        </tr>`).join('');

      container.innerHTML += `<table border="1">${headerHTML}${bodyHTML}</table>`;
    }
  }
}

// Example usage with an API URL and container ID
domain = "http://localhost:8080/api/v1";
path = "/events"; // Example URL
containerId = 'tb_eventsL';
fetchDataAndRenderRow(domain, path, containerId);
