var express = require('express');
var mongoose = require('mongoose');
var app = express();

var bodyParser = require('body-parser');
app.use(bodyParser.json({ type: 'application/json' }));
// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));


app.use(function(req, res, next) {
   res.setHeader('Access-Control-Allow-Origin', 'http://localhost:4200');
   res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
   res.header('Access-Control-Allow-Methods', 'POST, GET, PUT, DELETE, OPTIONS');
   next();
});

mongoose.connect('mongodb://localhost/emberData');

var noteSchema = new mongoose.Schema({
   title: 'string',
   content: 'string',
   author: 'string'
});

var NoteModel = mongoose.model('note', noteSchema);
app.get('/api', function(req, res) {
   res.send('Working');
});


var workerSchema = new mongoose.Schema({
   name: 'string',
   age:'number',
   wage: 'number',
   active: 'boolean'
});



var WorkerModel = mongoose.model('worker', workerSchema);


var studentSchema = new mongoose.Schema({
   name: 'string',
   age: 'number',
   wage: 'number',
   active: 'boolean'
});

var StudentModel = mongoose.model('student', studentSchema);

app.get('/api/notes', function(req, res) {
  NoteModel.find({}, function(err, docs) {
    console.log({note:docs});
    if(err) {
       res.send({error:err});
    } else {
       res.send({note:docs});
    }
  });
});


app.get('/api/workers', function(req, res)  {
    WorkerModel.find({}, function(err, docs) {
       if(err)  {
          res.send(err);
      } else {
          res.send(docs);
      }
   });
});




app.post('/api/workers', function(req,res)  {
    console.log(req.body);
    if(req.body.worker) req.body = req.body.worker;
    var id = req.body.id;
    var name = req.body.name;
    var age = req.body.age;
    var wage = req.body.wage;
    var active = req.body.active;
    var worker = new WorkerModel({
    id: id ,
    name: name,
    age: age,
    wage: wage,
    active: active
    });

    console.log(id, name, age, wage, active);

    //console.log(worker);

     worker.save(function(err, w) {
       if(err) res.send(err);
       else res.send(w);
    });
});


app.post('/api/workers/all', function(req,res)  {
  //  console.log(req.body);
   var workers = req.body.workers;
  for(var i=0; i< workers.length; ++i) {
      var id = workers[i].id;
    var name = workers[i].name;
    var age = workers[i].age;
    var wage = workers[i].wage;
    var active = workers[i].active;
    var worker = new WorkerModel({
    id: id ,
    name: name,
    age: age,
    wage: wage,
    active: active
    });

    //console.log(worker);
      var flag = false;
     worker.save(function(err, w) {
       if(err) {
res.send(err);
flag = true;
}
      // else res.send({worker:w});
    });

   if(flag) break;
}
  res.send("Added all successfully");

});



app.delete('/api/workers/delete/v0', function(req, res) {
    WorkerModel.deleteMany({__v:0}, function(err, raw)  {
        if(err) res.send(err);
        else res.send(raw);
  });
});

app.delete('/api/workers/delete/all', function(req, res) {
   WorkerModel.find({}, function(err, docs) {
        if(err) res.send(err);
        else {
console.log(docs);
    var flag = false;
        for(var i=0; i< docs.length; ++i) {
 WorkerModel.deleteOne({_id:docs[i]._id}, function(err2, raw) {
          if(err2) { res.send(err2);
 flag = true;
}
     //     else res.send(raw);
        });

  if(flag) break;
}
  res.send("Deleted all rows");

       }
   });
});


app.get('/api/workers/:id', function(req, res) {
    var id = req.params.id;
    WorkerModel.find({_id:id}, function(err, docs) {
        if(err)  {
          res.send(err);
      } else {
          res.send(docs[0]);
      }
});
});

app.patch('/api/workers/:id', function(req, res) {
      var id = req.params.id;
   if(req.body.worker) req.body = req.body.worker;
      var name = req.body.name;
      var age = req.body.age;
      var wage = req.body.wage;
      var active = req.body.active;
      WorkerModel.updateOne({_id:id}, {_id:id, name:name, age:age, wage:wage, active:active}, function(err2, raw) {
        if(err2) res.send(err2);
        else res.send(raw);
   });
});




app.put('/api/workers/:id', function(req, res) {
      console.log("req.params=" , req.params);
      console.log("req.body=", req.body);
   if(req.body.worker) req.body = req.body.worker;
      var id = req.params.id;
      var name = req.body.name;
      var age = req.body.age;
      var wage = req.body.wage;
      var active = req.body.active;
      WorkerModel.updateOne({_id:id}, {_id:id, name:name, age:age, wage:wage, active:active}, function(err2, raw) {
        if(err2) res.send(err2);
        else res.send(raw);
   });
});

app.delete('/api/workers/:id', function(req, res) {
    var id = req.params.id;
    WorkerModel.deleteOne({_id:id}, function(err, raw) {
       if(err) res.send(err);
       else res.send(raw);
  });
});






//student
app.get('/api/students', function(req, res)  {
    StudentModel.find({}, function(err, docs) {
       if(err)  {
          res.send({error:err});
      } else {
          res.send({student:docs});
      }
   });
});




app.post('/api/students', function(req,res)  {
    console.log(req.body);
    if(req.body.worker) req.body = req.body.worker;
    var id = req.body.id;
    var name = req.body.name;
    var age = req.body.age;
    var wage = req.body.wage;
    var active = req.body.active;
    var student = new StudentModel({
    id: id ,
    name: name,
    age: age,
    wage: wage,
    active: active
    });

    console.log(id, name, age, wage, active);

    //console.log(worker);

     student.save(function(err, w) {
       if(err) res.send({error:err});
       else res.send({student:w});
    });
});


app.post('/api/students/all', function(req,res)  {
  //  console.log(req.body);
   var students = req.body.students;
  for(var i=0; i< students.length; ++i) {
      var id = students[i].id;
    var name = students[i].name;
    var age = students[i].age;
    var wage = students[i].wage;
    var active = students[i].active;
    var student = new StudentModel({
    id: id ,
    name: name,
    age: age,
    wage: wage,
    active: active
    });

    //console.log(worker);
      var flag = false;
     student.save(function(err, w) {
       if(err) {
res.send({error:err});
flag = true;
}
      // else res.send({worker:w});
    });

   if(flag) break;
}
  res.send("Added all successfully");

});


let baseUrl = "/api/mem_workers";
app.get(baseUrl, function(req, res) {
  let workers =  [
    {id: 1, name: "foo", age: 10, wage: 100.0, active: true}, 
    {id: 2, name: "bar", age: 20, wage: 200.0, active: false}
  ];
  res.send(workers);
});

// app.delete

app.listen('4500');
