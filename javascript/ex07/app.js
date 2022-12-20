// express 라이브러리 로딩하기
const express = require('express');

// HTTP 요청을 다루는 라이브러리 로딩하기
const request = require('request');

// POST 요청으로 보낸 payload 를 분석하는 라이브러리 로딩하기
const bodyParser = require('body-parser');

const port = 3000;  // 웹서버 포트 번호

// express()를 호출하여 웹서버를 준비한다.
const app = express();

// POST 요청으로 보낸 payload 데이터를 분석할 객체를 지정하기
// => Content-Type: application/x-www-form-urlencoded 형식으로 된 payload 처리
//    예) name=hong&age=20
app.use(express.urlencoded({extended: true}));

// 클라이언트 요청에 대해 호출될 메서드를 등록
app.get(                 // GET 요청이 들어 왔을 때 호출될 메서드 지정
  '/exam01-1',           // 요청 URL
  (req, res) => {        // 요청 핸들러: 요청이 들어 왔을 때 호출되는 메서드
    res.set('Access-Control-Allow-Origin', '*');  // CORS 문제 해결
    res.set('Content-Type', 'text/plain; charset=UTF-8');
    res.send('Hello!');
  } 
); 

app.get('/exam02-1', (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/plain; charset=UTF-8');
    
    var payload = `이름: ${req.query.name}\n`;
    payload += `나이: ${req.query.age}\n`;

    res.send(payload);
});

app.post('/exam02-2', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  
  var payload = `이름: ${req.body.name}\n`;
  payload += `나이: ${req.body.age}\n`;

  res.send(payload);
});

app.get('/exam03-1', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');

  setTimeout(()=> {
    res.send("Hello");
  }, 15000);

  });

  app.get('/exam03-4', (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/plain; charset=UTF-8');
  
    let.query.a = parseInt(req.query.a);
    let.query.b = parseInt(req.query.b);
    
    res.send(`${a / b}`);
  
    });  

  app.get('/header', (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/html; charset=UTF-8');
  
    res.send('<h1>비d트캠프 네이버 클라우드 AIaaS과정</h1>');
    });

  app.get('/footer', (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/html; charset=UTF-8');
  
    res.send('<address>비트캠프 tjchzoavm@2022</address>');
    });

  app.get('/exam04-3', (req, res) => {
    res.set('Access-Control-Allow-Origin', '*');
    res.set('Content-Type', 'text/html; charset=UTF-8');

    let arr = [
      {no:1,title:'제목1',writer:'홍길동',viewCnt:19},
      {no:2,title:'제목2',writer:'임꺽정',viewCnt:312},
      {no:3,title:'제목3',writer:'유관순',viewCnt:31},
      {no:4,title:'제목4',writer:'안중근',viewCnt:100},
      {no:5,title:'제목5',writer:'윤봉길',viewCnt:200},
    ];

    // 배열 객체를 JSON 문자열로 변환하여 클라이언트에게 보낸다
    // => serialization(직렬화)
    res.send(JSON.stringify(arr));
  });
    
// 클라이언트 요청을 다른 서버에게 보낸다.
app.get('/proxy', (req, res) => {

  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  
  request.get({
    uri: req.query.url
  }, (error, response, body) => {
    res.send(body);
  });


}); 

app.post('/login', (req, res) => {
  res.set('Access-Control-Allow-Origin', '*');
  res.set('Content-Type', 'text/plain; charset=UTF-8');
  
  var payload = `이메일: ${req.body.email}\n`;
  payload += `암호: ${req.body.password}\n`;

  res.send(payload);
});

// 웹서버 실행하기
app.listen(
  3000,       // 포트 번호 지정
  () => {     // 서버가 시작되었을 때 호출될 함수 = 리스너 = 이벤트 핸들러
    console.log(`${port}번 포트에서 서버 시작했음!`);
  } 
);

