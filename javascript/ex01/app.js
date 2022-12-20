const { rejects } = require('assert');
const express = require('express');
// 'const { resolve } = require('path');
// const { allowedNodeEnvironmentFlags } = require('process');'

const app = express()
const port = 3000

app.get('/exam05_1', (req, res) => {
  await new Promise(resolve => setTimeout(resolve,10000));
  res.send('console.log("exam05_1 ok!");')
})

app.get('/exam05_2', (req, res) => {
  res.send('console.log("exam05_2 ok!");')
})

app.get('/exam05_x', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve,10000));
  res.send('var rate = 30000;')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
})

var p1 = new(resolve, reject) ({
  console.log("작업수행!");
  resolve();
});

function success() {
  console.log("success!");
}

function fail() {
  console.log("fail!");
}

function final() {
  console.log("completed!");
}

app.get('/hello', function(req, res) {

  var p1 = new Promise(execute);
  p1.then(success);
  p1.catch(fail);
  p1.finally(final);

  res.send('Hello!');
});