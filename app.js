var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var ejs = require('ejs');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var signupRouter = require('./routes/signup-router');
var loginRouter = require('./routes/login-router');
var userInputRouter = require('./routes/user-input-router');

var graphRouter = require('./routes/graph-router');
var appUserjsonRouter = require('./routes/appuserjson-router');
var webDailyRouter = require('./routes/web-daily-router');
var webFoodRouter = require('./routes/web-food-router');
var trainerRouter = require('./routes/trainer-router');
var webWorkoutRouter = require('./routes/web-workout-router');
var webQaRouter = require('./routes/web-qa-router');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// port setup
app.set('port', process.env.PORT || 3001);

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/uploads',express.static(__dirname + '/uploads'));
app.use('/views',express.static(__dirname + '/views'));

app.engine('html', ejs.renderFile);

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/signup', signupRouter);
app.use('/login', loginRouter);
app.use('/userinput', userInputRouter);

app.use('/graph', graphRouter);
app.use('/appuserjson', appUserjsonRouter);
app.use('/dailyjson', webDailyRouter);
app.use('/foodjson', webFoodRouter);
app.use('/trainerjson', trainerRouter);
app.use('/workoutjson', webWorkoutRouter);
app.use('/post',webQaRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;

var server = app.listen(app.get('port'), function() {
console.log('Express server listening on port ' + server.address().port);
});
