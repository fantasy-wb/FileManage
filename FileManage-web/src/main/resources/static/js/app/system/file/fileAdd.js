$("#file-add .btn-close").click(function () {
    closeModal();
});

function closeModal() {
    $MB.closeAndRestModal("file-add");
}