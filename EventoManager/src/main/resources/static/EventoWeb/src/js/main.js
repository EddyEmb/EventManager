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

function renderRow(data, containerId) {
  const container = document.getElementById(containerId);
  container.innerHTML = !data ? '<p>No data available</p>' : `
    <table border="1">
      <tr>${Object.keys(data).map(key => `<th>${key === 'communityResponse' ? 'Community' : key}</th>`).join('')}</tr>
      <tr>${Object.entries(data).map(([key, value]) => {
        if (key === 'communityResponse' && typeof value === 'object' && value !== null) {
          return `<td>${value.name}</td>`;
        } else {
          return `<td>${value}</td>`;
        }
      }).join('')}</tr>
    </table>`;
}



function renderList(data, containerId) {
  const container = document.getElementById(containerId);
  container.innerHTML = !data || !data.length ? '<p>No data available</p>' : `
    <table border="1">
      <tr>${Object.keys(data[0]).map(key => `<th>${key}</th>`).join('')}</tr>
      ${data.map(item => `<tr>${Object.values(item).map(value => `<td>${value}</td>`).join('')}</tr>`).join('')}
    </table>`;
}

async function fetchDataAndRenderRow(domain, path, containerId) {
  try {
    const url = `${domain}${path}`;
    const data = await fetchData(url);

    if (Array.isArray(data)) {
      if (data.length > 0 && typeof data[0] === 'object') {
        renderRow(data[0], containerId);
        renderList(data.slice(1), containerId);
      } else {
        renderList(data, containerId);
      }
    } else if (typeof data === 'object') {
      renderRow(data, containerId);
    } else {
      console.error('Invalid data format:', data);
    }
  } catch (error) {
    console.error('Error:', error);
  }
}



// Example usage with an API URL and container ID
const domain = "http://localhost:8080/api/v1";
let path = "/communities"; // Example URL
let containerId = 'tb_communities';
fetchDataAndRenderRow(domain, path, containerId);

path = "/events/event/3"; // Example URL
containerId = 'tb_events';
fetchDataAndRenderRow(domain, path, containerId);
