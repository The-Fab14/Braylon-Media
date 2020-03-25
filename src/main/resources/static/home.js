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
    // tableBody.empty();
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
        '<td class="clientData">' + client.emailAddress + '</td>' + 
        '<td class="clientData"><button class="btn btn-link"><a href="/edit_customer/' + client.clientId + '">Edit</a></button></td>'
        );
    });
}

function populateQueryDropdown(clients){
    $('#searchParameters').change(function (e) {
        const parameter = $(this).children('option:selected').attr('id');
        const resultDropdown = $('#searchQueries');
        // set the second dropdown to the default option each time user chooses a search parameter
        document.getElementById("defaultResult").selected = true;
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
            // Does NOT populate the dropdown, displays the default client view
            case 'viewAllClients':
                clearQueries();
                displayDefaultClientView(clients);
                break;
            default:
                alert('You broke this somehow');
                break;
        }
    });
}

/*
Each "populate" method populates the second <select> menu with search queries
*/
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

// Executes when 'View All Clients' is clicked, as query dropdown needs to be empty.
function clearQueries(){
    $('.query').remove();
}

/*
Each search function appends any matching search results to the table body
*/
function searchByClientId(clients, query){

    clients.forEach(client => {
        // 'query' is passed in as a string, hence the double equals operator instead of the triple
        if (client.clientId == query){
            $('tbody').append('<tr class="clientRow">' +
            '<td class="clientData">' + client.companyName + '</td>' +
            '<td class="clientData">' + client.contactLastName + '</td>' +
            '<td class="clientData">' + client.contactFirstName + '</td>' +
            '<td class="clientData">' + client.streetAddress + '</td>' +
            '<td class="clientData">' + client.aptUnit + '</td>' +
            '<td class="clientData">' + client.city + '</td>' +
            '<td class="clientData">' + client.state.stateId + '</td>' +
            '<td class="clientData">' + client.zip + '</td>' +
            '<td class="clientData">' + client.phoneNumber + '</td>' +
            '<td class="clientData">' + client.emailAddress + '</td>' + 
            '<td class="clientData"><button class="btn btn-link"><a href="/edit_customer/' + client.clientId + '">Edit</a></button></td>'
            );
        }
    });
}

function searchByCompanyName(clients, query){

    clients.forEach(client => {
        if (client.companyName == query){
            $('tbody').append('<tr class="clientRow">' +
            '<td class="clientData">' + client.companyName + '</td>' +
            '<td class="clientData">' + client.contactLastName + '</td>' +
            '<td class="clientData">' + client.contactFirstName + '</td>' +
            '<td class="clientData">' + client.streetAddress + '</td>' +
            '<td class="clientData">' + client.aptUnit + '</td>' +
            '<td class="clientData">' + client.city + '</td>' +
            '<td class="clientData">' + client.state.stateId + '</td>' +
            '<td class="clientData">' + client.zip + '</td>' +
            '<td class="clientData">' + client.phoneNumber + '</td>' +
            '<td class="clientData">' + client.emailAddress + '</td>' + 
            '<td class="clientData"><button class="btn btn-link"><a href="/edit_customer/' + client.clientId + '">Edit</a></button></td>'
            );
        }
    });
}

function searchByContactLastName(clients, query){

    clients.forEach(client => {
        if (client.contactLastName == query){
            $('tbody').append('<tr class="clientRow">' +
            '<td class="clientData">' + client.companyName + '</td>' +
            '<td class="clientData">' + client.contactLastName + '</td>' +
            '<td class="clientData">' + client.contactFirstName + '</td>' +
            '<td class="clientData">' + client.streetAddress + '</td>' +
            '<td class="clientData">' + client.aptUnit + '</td>' +
            '<td class="clientData">' + client.city + '</td>' +
            '<td class="clientData">' + client.state.stateId + '</td>' +
            '<td class="clientData">' + client.zip + '</td>' +
            '<td class="clientData">' + client.phoneNumber + '</td>' +
            '<td class="clientData">' + client.emailAddress + '</td>' + 
            '<td class="clientData"><button class="btn btn-link"><a href="/edit_customer/' + client.clientId + '">Edit</a></button></td>'
            );
        }
    });
}

function searchBySalesRepId(clients, query){

    clients.forEach(client => {
        if (client.user.userId == query){
            $('tbody').append('<tr class="clientRow">' +
            '<td class="clientData">' + client.companyName + '</td>' +
            '<td class="clientData">' + client.contactLastName + '</td>' +
            '<td class="clientData">' + client.contactFirstName + '</td>' +
            '<td class="clientData">' + client.streetAddress + '</td>' +
            '<td class="clientData">' + client.aptUnit + '</td>' +
            '<td class="clientData">' + client.city + '</td>' +
            '<td class="clientData">' + client.state.stateId + '</td>' +
            '<td class="clientData">' + client.zip + '</td>' +
            '<td class="clientData">' + client.phoneNumber + '</td>' +
            '<td class="clientData">' + client.emailAddress + '</td>' + 
            '<td class="clientData"><button class="btn btn-link"><a href="/edit_customer/' + client.clientId + '">Edit</a></button></td>'
            );
        }
    });
}

// displays matching results when an <option> element(search query) is clicked. (Will not execute when 'View All Clients' is clicked)
function displaySearchResults(clients){
    // dropdown element (<option>) is clicked
    $('#searchQueries').change(function (event) {
        event.preventDefault();
        // grab the element's ID
        const query = $(this).children('option:selected').text();
        // grab the element's parent (<select>) ID
        const searchParameter = $('#searchParameters').children('option:selected').attr('id');
        console.log('searchParameter: ', searchParameter);
        // empty the table body
        $('tbody').empty();

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
                alert("Congrats, you broke this");
                break;
        }
    });
}