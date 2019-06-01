
$("#file-add .btn-close").unbind('click').click(function () {
    //alert("1111");
    $MB.closeAndRestModal("file-add");
    $MB.refreshTable("fileTable");
});

$("#dir-add .btn-close").unbind('click').click(function () {
    $('#dirName').val("");
    $MB.closeAndRestModal("dir-add");
    $MB.refreshTable("fileTable")
});

function checkFileOrDirName(fileName) {

    if(fileName.contains('/')){
        return false;
    }
    return true;
}

$("#dir-add .btn-save").unbind('click').click(function () {

    var dirName = $("#dirName").val();
    //校验文件名是否合法
    var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]");//,"i");
    if(pat.test(dirName)){
        alert("输入文件名非法");
        $('#dirName').val("");
        return;
    }

    $.post(ctx + "file/addDir", {
        fileName: dirName,
        parentUrl: indexUrl,
        fileType: "dir"
    }, function (r) {

        if (r.code === 0) {
            $('#dirName').val("");
            $MB.closeAndRestModal("dir-add");
            $MB.n_success(r.msg);
            $MB.refreshTable('fileTable');
        } else $MB.n_danger(r.msg);
    });

});