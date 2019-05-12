$("#file-add .btn-close").click(function () {
    closeModal();
});

function closeModal() {
    $MB.closeAndRestModal("file-add");
}

function checkFileOrDirName(fileName) {

    if(fileName.contains('/')){
        return false;
    }

    return true;

}