/**
 * 合并js,css文件
 */
with (document) {
	// 新 Bootstrap 核心 CSS 文件
	write('<link rel="stylesheet" href="../css/bootstrap.min.css">');
	// 可选的Bootstrap主题文件（一般不用引入）
	write('<link rel="stylesheet" href="../css/bootstrap-theme.min.css">');

	write('<link rel="stylesheet" href="../css/dropdown-submenu.css">');
	// 使用表格插件
	write('<link rel="stylesheet" href="../css/bootstrap-table.css">');

	write('<link rel="stylesheet" href="../css/bootstrap-select.min.css">');

	write('<link rel="stylesheet" href="../css/jquery-ui.min.css">');

	write('<link rel="stylesheet" href="../css/datetimepicker.css">');
	

	// <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	write('<script type="text/javascript" language="javascript" src="../js/jquery-1.11.1.min.js"></script>');
	// <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	write('<script type="text/javascript" language="javascript" src="../js/bootstrap.min.js"></script>');

	/* 引入表格插件 */
	write('<script type="text/javascript" language="javascript" src="../js/bootstrap-table.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/bootstrap-table-zh-CN.js"></script>');

	/* 版本为 Bootstrap-select v1.6.4 */
	write('<script type="text/javascript" language="javascript" src="../js/bootstrap-select.min.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/defaults-zh_CN.min.js"></script>');

	/* 引入日历插件 */
	write('<script type="text/javascript" language="javascript" src="../js/jquery-ui.min.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/jquery-ui-timepicker-addon.min.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/jquery-ui-timepicker-zh-CN.js"></script>');

	/* 引入jstree */
	write('<link rel="stylesheet" href="../js/jstree/style.min.css">');
	write('<script type="text/javascript" language="javascript" src="../js/jstree/jstree.min.js"></script>');
	/* 引入倒计时插件 */
	write('<script type="text/javascript" language="javascript" src="../js/jquery.plugin.min.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/jquery.countdown.js"></script>');
	write('<script type="text/javascript" language="javascript" src="../js/jquery.countdown-zh-CN.js"></script>');

}
