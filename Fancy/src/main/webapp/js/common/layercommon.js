var msgtime = 1000;//layer.msg 弹出时间

/******************提示icon定义********************/
var success_icon = 9; // 操作成功
var fail_icon = 8; // 失败提示
var warn_icon = 0; // 警告提示
var delete_icon = 4; // 删除提示
var confirm_icon = 4; // confirm提示

/******************提示背景定义********************/
var layer_shade = [0.3, '#393D49']; // [x,y]x越大阴影颜色越深，y颜色

/******************提示样式定义********************/
var warn_tips = {icon: warn_icon,time:msgtime,shade:layer_shade}; // 警告ps：请选择操作数据！等类似提示可用此项
var delete_tips = {icon: delete_icon}; // 删除
var success_tips = {icon: success_icon,time:msgtime,shade:layer_shade}; // 操作成功
var fail_tips = {icon: fail_icon,time:msgtime,shade:layer_shade}; // 操作失败
var confirm_tips = {icon: confirm_icon,zIndex:9999}; // confirm提示参数
