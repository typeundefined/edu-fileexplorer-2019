$(document).ready(function () {

    $('#search').on('input', callSearch);
    $('#search_action').click( callSearch);
});

function changeSearch(elem) {
    $('#search_action').text(elem.innerText);
};
function callSearch() {
    let searchParams = new URLSearchParams(window.location.search);
    let request;
    let url;
    let btn = $('#search_action');
    if ($(btn).text() === 'Search in children')
        url = '/searchAll';
    if ($(btn).text() === 'Search Here')
        url = '/search';
    let path = searchParams.has('path') ? searchParams.get('path') : "";
    request = jQuery.ajax({
        type: 'GET',
        url: url,
        data: {
            search: $('#search').val(),
            path: path
        },
        beforeSend: function () {
            if (request != null) {
                request.abort();
            }
        },
        success: function (data) {
            $('#files_container').html(data);
        },
        error: function (e) {
            // Error
        }
    })
}

