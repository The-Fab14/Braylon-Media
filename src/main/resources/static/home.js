$(document).ready(function () {
    
    $.when(getCustomers(userId)).done(function(clients){
    // display results in each dropdown
        // customer number sorting
        // company sorting
        // contact name sorting
        // sales rep ID sorting

    // display results in alphabetical order (contact last name)
    displayDefaultCustomerView(clients);

    //populate dropdown search boxes
    populateSearchByClientIdDropdown(clients);
    populateSearchByCompanyDropdown(clients);
    populateSearchByContactLastNameDropdown(clients);
    populateSearchBySalesRepId(clients);
    });
});

// AJAX call
function getCustomers(userId){
    return $.ajax({
        type: "GET",
        url: "http://localhost:8080/", //incomplete URL, "/clients/userId"
        success: function (response) {
            
        },
        error: function(xhr){
            alert("Request status: " + xhr.status + " Status text: " + xhr.statusText + " " + xhr.responseText);
        }
    });
}

function displayDefaultCustomerView(clients){
    const tbody = $('tbody');
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
        tbody.append('<tr>' +
        '<td>' + client.companyName + '</td>' +
        '<td>' + client.contactLastName + '</td>' +
        '<td>' + client.contactFirstName + '</td>' +
        '<td>' + client.streetAddress + '</td>' +
        '<td>' + client.aptUnit + '</td>' +
        '<td>' + client.city + '</td>' +
        '<td>' + client.state + '</td>' +
        '<td>' + client.zip + '</td>' +
        '<td>' + client.phoneNumber + '</td>' +
        '<td>' + client.emailAddress + '</td>' +
        );
    });
}

function populateSearchByClientIdDropdown(clients){
    // sort clients by customer number
    const clientsByClientId = clients.sort((a, b) => {
        if (a.clientId > b.clientId){
            return true;
        }
        return false;
    });

    // populate dropdown
    const dropdown = $('#customerNumber');
    clientsByClientId.forEach(client => {
        dropdown.append('<option' +
            'value="' + client.clientId + '" ' +
            'id="' + client.clientId + '">' + 
            client.clientId +
            '</option>'
        );
    });
}

function populateSearchByCompanyDropdown(clients){
    // sort clients by company name
    const clientsByCompanyName = clients.sort((a, b) => {
        if (a.companyName > b.companyName){
            return true;
        }
        return false;
    });

    // populate dropdown
    const dropdown = $('#company');
    clientsByCompanyName.forEach(client => {
        dropdown.append('<option' +
            'value="' + client.companyName + '" ' +
            'id="' + client.companyName + '">' + 
            client.companyName +
            '</option>'
        );
    });
}

function populateSearchByContactLastNameDropdown(clients){
    // sort clients by contact lastname
    const clientsByContactLastName = clients.sort((a, b) => {
        if (a.contactLastName > b.contactLastName){
            return true;
        }
        return false;
    });

    // populate dropdown
    const dropdown = $('#contactLastName');
    clientsByContactLastName.forEach(client => {
        dropdown.append('<option' +
            'value="' + client.contactLastName + '" ' +
            'id="' + client.contactLastName + '">' + 
            client.contactLastName +
            '</option>'
        );
    });
}

function populateSearchBySalesRepId(clients){

}

// Refactoring in progress: pass in an array of clientIds instead of clients, dropdown selector
//function populateSearchByClientId2(clients)