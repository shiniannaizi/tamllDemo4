$(function() {
    var $ = jQuery,
    // 展示图片列表的容器
    $list = $('#fileList'), 
    // 优化retina, 在retina下这个值是2
    ratio = window.devicePixelRatio || 1,

    // 缩略图大小，像素*像素
    thumbnailWidth = 100 * ratio,
    thumbnailHeight = 100 * ratio,

    // Web Uploader实例
    uploader;
    

    // 初始化Web Uploader
    var uploader = WebUploader.create({

        // 选完文件后，是否自动上传。
        auto: true,

        // swf文件路径
        swf: 'http://localhost:8080/DataTables/js/Uploader.swf',

        // 文件接收服务端。
        server: 'http://localhost:8080/backend/addprodimageinfo/',

        //fileVal : "file", // 指定input标签name的属性值
        
        threads:'5',        //同时运行5个线程传输
        fileNumLimit:'10',  //文件总数量只能选择10个 

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        // pick: '#filePicker',
        
        pick: { id:'#filePicker',  //选择文件的按钮
                multiple:true }, 

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    
    // 当有文件添加进来的时候，
     //监听fileQueued事件，通过uploader.makeThumb来创建图片预览图。
    //PS: 这里得到的是Data URL数据，IE6、IE7不支持直接预览。可以借助FLASH或者服务端来完成预览。
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');
         // $list为容器jQuery实例  
         $list.append( $li );

 
        // 创建缩略图,  thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {  //webuploader方法  
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
             $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );

    });


    // 然后剩下的就是上传状态提示了，当文件 上传过程中, 上传成功，上传失败，上传完成
    // 都分别对应uploadProgress, uploadSuccess, uploadError, uploadComplete事件。

    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');
 

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

 
        $percent.css( 'width', percentage * 100 + '%' );
    });
 

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file,response) {
        $( '#'+file.id ).addClass('upload-state-done');
        
    });

 
    // 文件上传失败，现实上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');
 

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }
 
        $error.text('上传失败');

    });

 
    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });


    //绑定提交事件
    $("#btn").click(function() {
        console.log("上传...");
        uploader.upload();   //执行手动提交
        console.log("上传成功");
     });
    
});