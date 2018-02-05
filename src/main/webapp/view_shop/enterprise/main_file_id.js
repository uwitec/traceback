var files = new Array();
function initUploader(i) {
	if(i==undefined){i=""};
	$list = $('#thelist'+i);
	$btn = $('#ctlBtn'+i);
	var state = 'pending';

	var uploader = WebUploader.create({
		// 选完文件后，是否自动上传。
		auto : true,
		// swf文件路径
		swf : 'http://cdn.staticfile.org/webuploader/0.1.5/Uploader.swf',

		duplicate : true,
		// 文件接收服务端。
		server : '/traceback/upload.htm',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick : '#picker' + i,

		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize : false
	});

	// 当有文件被添加进队列的时候
	uploader.on('fileQueued', function(file) {
		$list.append('<div id="' + file.id + '" class="item">' +
		// '<h4 class="info">' + file.name + '</h4>' +
		// '<p class="state">等待上传...</p>' +
		'</div>');
	});
	// 文件上传过程中创建进度条实时显示。
	uploader
			.on(
					'uploadProgress',
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress .progress-bar');

						// 避免重复创建
						if (!$percent.length) {
							$percent = $(
									'<div class="progress progress-striped active">'
											+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
											+ '</div>' + '</div>')
									.appendTo($li).find('.progress-bar');
						}
						//
						// $li.find('p.state').text('上传中');

						$percent.css('width', percentage * 100 + '%');
					});
	uploader.on('uploadSuccess', function(file, response) {
		// $( '#'+file.id ).find('p.state').text('已上传');
		//console.log(response)
		var end = $.parseJSON(response._raw);
		$("#logo_img" +i).attr("src","/traceback/img/"+end.file_id+".htm?mh=120&mw=120");
		if(i == 0){
			console.log("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
			$("input[name=yimg_url]").val("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
		}else if(i == 1){
			console.log("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
			$("input[name=simg_url]").val("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
		}else if(i == 3){
			console.log("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
			$("input[name=yimg_url]").val("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
		}else if(i == 4){
			console.log("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
			$("input[name=simg_url]").val("http://"+window.location.host+"/traceback/img/"+end.file_id+".htm");
		}
	});

	uploader.on('uploadError', function(file) {
		$('#' + file.id).find('p.state').text('上传出错');
	});

	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
	});

	$btn.on('click', function() {
		if (state === 'uploading') {
			uploader.stop();
		} else {
			uploader.upload();
		}
	});

}