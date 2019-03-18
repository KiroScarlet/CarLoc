var fs = require('fs');
var http = require('http');
var socket = require('socket.io');
var pg = require('pg');
var util=require('util');

var constr=util.format('%s://%s:%s@%s:%s/%s', 'postgres','think8848','111111','localhost',5432,'chinaosmgisdb');
var server = http.createServer(function(req, res) {
		res.writeHead(200, { 'Content-type': 'text/html'});
		res.end(fs.readFileSync(__dirname + '/index.html'));
}).listen(8081, function() {
		console.log('Listening at: http://localhost:8081');
});

var pgClient = new pg.Client(constr);//数据库连接
var socketio=socket.listen(server);//socketio
socketio.on('connection', function (socketclient) {
	console.log('已连接socket:');
	//socketclient.broadcast.emit('GPSCoor', data.payload);//广播给别人
	   //socketclient.emit('GPSCoor', data.payload);//广播给自己

});
var sql = 'LISTEN gps'; //监听数据库的gps消息
var query = pgClient.query(sql);//开始数据库消息监听
      //数据库一旦获取通知，将通知消息通过socket.io发送到各个客户端展示。
pgClient.on('notification', function (data) {
	console.log(data.payload);
	//socketio.sockets.emit('GPSCoor', data.payload); //与下面的等价
	   socketio.emit('GPSCoor', data.payload);//广播给所有的客户端

});
pgClient.connect();