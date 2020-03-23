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
    return $.ajax({
        type: "GET",
        url: "http://localhost:8080/clients/" + userId,
        success: function (response) {},
        error: function(xhr){
            alert("Request status: " + xhr.status + " Status text: " + xhr.statusText + " " + xhr.responseText);
        }
    });
}

// Runs on page load to display the default table view of clients (sorted by contact last name)
function displayDefaultClientView(clients){
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
        tbody.append('<tr class="clientRow">' +
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
    $('#searchParameter').change(function (e) { 
        alert('selector works');

        const parameter = $(this).children('option:selected').attr('id');
        const resultDropdown = $('#searchResults');

        switch(parameter){
            case 'clientId':
                clients.forEach(client => {
                    resultDropdown.append('<option>' + client.clientId + '</option>')
                });
                break;
        }
    });
}


function matchByClientId(clients, elementId){
    clients.forEach(client => {
        if (client.clientId === elementId){
            
        }
    });
}

function matchByCompanyName(clients, elementId){

}

function matchByContactLastName(clients, elementId){

}

function matchBySalesRepId(clients, elementId){

}

// displays matching results when an <option> element is clicked
function displaySearchResults(clients){
    // dropdown element (<option>) is clicked
    $('option').click(function (event) { 
        event.preventDefault();
        alert('option clicked');
        
        // grab the element's ID
        const elementId = $(this.attr('id'));
        console.log('elementId: ', elementId);

        // grab the element's parent (<select>) ID
        const searchParameter = $(this).parent().attr('id');
        console.log('searchParamter: ', searchParameter);

        // display only results that match that element
        switch(searchParameter){
            case "clientId":
                matchByClientId(clients, elementId);
                break;
            case "companyName":
                matchByCompanyName(clients, elementId);
                break;
            case "contactLastName":
                matchByContactLastName(clients, elementId);
                break;
            case "salesRepId":
                matchBySalesRepId(clients, elementId);
                break;
            default:
                alert("Not sure what's going on here");
        }
    });
}