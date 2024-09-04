function copyToClipboard() {
    navigator.clipboard.writeText($('#link').text());
    $('#copier').attr('data-bs-toggle', 'tooltip').attr('title', 'Copied!');
    $("[id='copier']").tooltip();
    $('#copier').blur().focus();
}