$(document).ready(function () {
    const userId = $('#userId').text();
    $.when(getCustomers(userId)).done(function(clients){
        // display results in alphabetical order (contact last name)
        displayDefaultClientView(clients);
        // populate search queries dropdown when a search parameter is clicked
        populateQueryDropdown(clients);
        displaySearchResults(clients);
    });
});
// AJAX call
function getCustomers(userId){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    return $.ajax({
        type: "GET",
        url: "http://localhost:8080/load-clients",
        success: function (response) {},
        error: function(xhr){
            alert("Request status: " + xhr.status + "/nStatus text: " + xhr.statusText + " " + xhr.responseText);
        }
    });
}
// Runs on page load to display the default table view of clients (sorted by contact last name)
function displayDefaultClientView(clients){
    const tableBody = $('tbody');
    console.log('clients: ', clients);
    // sort clientArray by contactLastName for default view
    const clientsByContactLastName = clients.sort((a, b) => {
        if (a.contactLastName > b.contactLastName){
            return true;
        }
        return false;
    });
    // append client details to rows
    clientsByContactLastName.forEach(client => {
        tableBody.append('<tr class="clientRow">' +
        '<td class="clientData">' + client.companyName + '</td>' +
        '<td class="clientData">' + client.contactLastName + '</td>' +
        '<td class="clientData">' + client.contactFirstName + '</td>' +
        '<td class="clientData">' + client.streetAddress + '</td>' +
        '<td class="clientData">' + client.aptUnit + '</td>' +
        '<td class="clientData">' + client.city + '</td>' +
        '<td class="clientData">' + client.state.stateId + '</td>' +
        '<td class="clientData">' + client.zip + '</td>' +
        '<td class="clientData">' + client.phoneNumber + '</td>' +
        '<td class="clientData">' + client.emailAddress + '</td>'
        );
    });
}
/*
    Functions for populating the 'search by' dropdowns
*/
// function populateDropdownByClientId(clients){
//     // sort clients by customer number
//     const clientsByClientId = clients.sort((a, b) => {
//         if (a.clientId > b.clientId){
//             return true;
//         }
//         return false;
//     });
//     // populate dropdown
//     const dropdown = $('#clientId');
//     clientsByClientId.forEach(client => {
//         dropdown.append('<option ' +
//             'value="' + client.clientId + '" ' +
//             'id="' + client.clientId + '">' +
//             client.clientId +
//             '</option>'
//         );
//     });
// }
// function populateDropdownByCompany(clients){
//     // sort clients by company name
//     const clientsByCompanyName = clients.sort((a, b) => {
//         if (a.companyName > b.companyName){
//             return true;
//         }
//         return false;
//     });
//     const dropdown = $('#companyName');
//     clientsByCompanyName.forEach(client => {
//         dropdown.append('<option ' +
//             'value="' + client.companyName + '" ' +
//             'id="' + client.companyName + '">' +
//             client.companyName +
//             '</option>'
//         );
//     });
// }
// function populateDropdownByContactLastName(clients){
//     // sort clients by contact lastname
//     const clientsByContactLastName = clients.sort((a, b) => {
//         if (a.contactLastName > b.contactLastName){
//             return true;
//         }
//         return false;
//     });
//     // populate dropdown
//     const dropdown = $('#contactLastName');
//     clientsByContactLastName.forEach(client => {
//         dropdown.append('<option ' +
//             'value="' + client.contactLastName + '" ' +
//             'id="' + client.contactLastName + '">' +
//             client.contactLastName +
//             '</option>'
//         );
//     });
// }
// function populateDropdownBySalesRepId(clients){
//     // sort clients by Sales Rep ID (lST view only)
//     const clientsBySalesRepId = clients.sort((a, b) => {
//         if (a.user.userId > b.user.userId){
//             return true;
//         }
//         return false;
//     });
//     // populate dropdown
//     const dropdown = $('#salesRepId');
//     clientsBySalesRepId.forEach(client => {
//         dropdown.append('<option ' +
//             'value="' + client.user.userId + '" ' +
//             'id="' + client.user.userId + '">' +
//             client.user.userId +
//             '</option>'
//         );
//     });
// }
function populateQueryDropdown(clients){
    $('#searchParameters').change(function (e) {
        const parameter = $(this).children('option:selected').attr('id');
        const resultDropdown = $('#searchQueries');
        switch(parameter){
            case 'clientId':
                populateWithClientIds(clients, resultDropdown);
                break;
            case 'companyName':
                populateWithCompanyNames(clients, resultDropdown);
                break;
            case 'contactLastName':
                populateWithContactLastName(clients, resultDropdown);
                break;
            case 'salesRepId':
                populateWithSalesRepId(clients, resultDropdown);
                break;
        }
    });
}

function populateWithClientIds(clients, resultDropdown){
    $('.query').remove();
    // Don't need to filter as client IDs are inherently unique
    clients.forEach(client => {
        resultDropdown.append('<option class="query">' + client.clientId + '</option>')
    });
}

function populateWithCompanyNames(clients, resultDropdown){
    $('.query').remove();
    // get unique company names
    const companies = [... new Set(clients.map(client => client.companyName))];
    companies.forEach(company => {
        resultDropdown.append('<option class="query">' + company + '</option>')
    });
}

function populateWithContactLastName(clients, resultDropdown){
    $('.query').remove();
    // get unique last names
    const lastNames = [... new Set(clients.map(client => client.contactLastName))];
    lastNames.forEach(lastName => {
        resultDropdown.append('<option class="query">' + lastName + '</option>')
    });
}

function populateWithSalesRepId(clients, resultDropdown){
    $('.query').remove();
    // get unique sales rep IDs
    const salesRepIds = [... new Set(clients.map(client => client.user.userId))];
    salesRepIds.forEach(salesRepId => {
        resultDropdown.append('<option class="query">' + salesRepId + '</option>')
    });
}

/*
Each search function clears the table body and appends any matching search results to it
*/
function searchByClientId(clients, query){
    const tableBody = $('tbody');
    // clear table body
    tableBody.empty();

    clients.forEach(client => {
        // 'query' is passed in as a string, hence the double equals operator instead of the triple
        if (client.clientId == query){
            tableBody.append('<tr class="clientRow">' +
            '<td class="clientData">' + client.companyName + '</td>' +
            '<td class="clientData">' + client.contactLastName + '</td>' +
            '<td class="clientData">' + client.contactFirstName + '</td>' +
            '<td class="clientData">' + client.streetAddress + '</td>' +
            '<td class="clientData">' + client.aptUnit + '</td>' +
            '<td class="clientData">' + client.city + '</td>' +
            '<td class="clientData">' + client.state.stateId + '</td>' +
            '<td class="clientData">' + client.zip + '</td>' +
            '<td class="clientData">' + client.phoneNumber + '</td>' +
            '<td class="clientData">' + client.emailAddress + '</td>'
            );
        }
    });
}
function searchByCompanyName(clients, query){
    const tableBody = $('tbody');
    // clear table body
    tableBody.empty();

    clients.forEach(client => {
        // 'query' is passed in as a string, hence the double equals operator instead of the triple
        if (client.companyName == query){
            tableBody.append('<tr class="clientRow">' +
            '<td class="clientData">' + client.companyName + '</td>' +
            '<td class="clientData">' + client.contactLastName + '</td>' +
            '<td class="clientData">' + client.contactFirstName + '</td>' +
            '<td class="clientData">' + client.streetAddress + '</td>' +
            '<td class="clientData">' + client.aptUnit + '</td>' +
            '<td class="clientData">' + client.city + '</td>' +
            '<td class="clientData">' + client.state.stateId + '</td>' +
            '<td class="clientData">' + client.zip + '</td>' +
            '<td class="clientData">' + client.phoneNumber + '</td>' +
            '<td class="clientData">' + client.emailAddress + '</td>'
            );
        }
    });
}
function searchByContactLastName(clients, query){
}
function searchBySalesRepId(clients, query){
}
// displays matching results when an <option> element is clicked
function displaySearchResults(clients){
    // dropdown element (<option>) is clicked
    $('#searchQueries').change(function (event) {
        event.preventDefault();
        console.log('option clicked');
        // grab the element's ID
        const query = $(this).children('option:selected').text();
        console.log('query: ', query);
        // grab the element's parent (<select>) ID
        const searchParameter = $('#searchParameters').children('option:selected').attr('id');
        console.log('searchParamter: ', searchParameter);
        // display only results that match that element
        switch(searchParameter){
            case "clientId":
                searchByClientId(clients, query);
                break;
            case "companyName":
                searchByCompanyName(clients, query);
                break;
            case "contactLastName":
                searchByContactLastName(clients, query);
                break;
            case "salesRepId":
                searchBySalesRepId(clients, query);
                break;
            default:
                alert("Not sure what's going on here");
        }
    });
}