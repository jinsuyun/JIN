var fs=require('fs');
var express=require('express');
var app=express();
var ejs=require('ejs');
var body_parser=require('body-Parser');
var http=require('http');
var mysql = require('mysql');

app.set('port', process.env.PORT || 3001);
app.set('view engine','html');
app.engine('html',ejs.renderFile);


var connection = mysql.createConnection({
    host:'18.221.204.247',
    port:'3306',
    user:'root',
    password:'5907',
    database:'capstone',
    debug:false
});


connection.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});

/*connection.query('SELECT * from appuser', function(err, rows, fields) {
  if (!err)
    console.log('The solution is: ', rows[0].id);
  else
    console.log('Error while performing Query.', err);
});

connection.end();*/



app.use(body_parser.urlencoded({extended: false}));
app.use(body_parser.json());
app.use('/static', express.static(__dirname + '/public'));

app.get('/',function(req,res){
    res.render('main.html');
    res.writeHead(200,{'Content-Type':'text/html'});
});

app.get('/graph',function(req,res){
    var id=req.params.id;
    res.render('graph.html');
});


app.get('/appuserjson', function(req,res){
    var appuser_query = connection.query('select * from appuser',function(err,rows){
         res.json(rows);
    });
});

app.get('/dailyjson', function(req,res){
    var appuser_query = connection.query('select * from daily',function(err,rows){
         res.json(rows);
    });
});

app.get('/foodjson', function(req,res){
    var appuser_query = connection.query('select * from food',function(err,rows){
         res.json(rows);
    });
});

app.get('/trainerjson', function(req,res){
    var appuser_query = connection.query('select * from trainer',function(err,rows){
         res.json(rows);
    });
});

app.get('/workoutjson', function(req,res){
    var appuser_query = connection.query('select * from workout',function(err,rows){
         res.json(rows);
    });
});

http.createServer(app).listen(app.get('port'),function(){
    console.log("body parser start : %d ", app.get('port'));    
});
