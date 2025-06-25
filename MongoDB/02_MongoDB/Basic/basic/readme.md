# 02 MongoDB BASIC


## 1. 자바스크립트 셸을 통한 MongoDB 기본

### 데이터베이스 선택
```javascript
use tutorial
```

### users 컬렉션에 문서 저장
```javascript
db.users.insert({username: "smith"})
db.users.insert({username: "jones"})
```

### 저장된 모든 문서 출력
```javascript
db.users.find()
```

### 저장된 문서 중 하나만 출력
```javascript
db.users.findOne()
```

### username이 "jones"인 문서 조회
```javascript
db.users.find({username: "jones"})
```

### username이 "jones" 또는 "smith"인 문서 조회
```javascript
db.users.find({
  $or: [{username: "smith"}, {username: "jones"}]
})
```

### username이 "smith"인 문서에 country가 "Canada"가 되도록 수정
```javascript
db.users.update(
  {username: "smith"},
  {$set: {country: "Canada"}}
)
```

### 수정 확인
```javascript
db.users.find({username: "smith"})
```

### username이 "smith"인 문서를 {country: "Canada"}로 대체
```javascript
db.users.update(
  {username: "smith"},
  {country: "Canada"}
)

db.users.find({country: "Canada"})
```

### country가 "Canada"인 문서를 {username: "smith", country: "Canada"}로 대체
```javascript
db.users.update(
  {country: "Canada"},
  {$set: {username: "smith", country: "Canada"}}
)

db.users.find({username: "smith"})
```

### username이 "smith"인 문서에서 country 키 삭제
```javascript
db.users.update(
  {username: "smith"},
  {$unset: {country: 1}}
)

db.users.find({username: "smith"})
```

### 데이터베이스 목록 출력
```javascript
show dbs
```

### 현재 데이터베이스의 컬렉션 목록 출력
```javascript
show collections
```

### 현재 데이터베이스와 users 컬렉션 상태 확인
```javascript
db.stats()
db.users.stats()
```

### username이 "smith"인 문서 삭제 및 확인
```javascript
db.users.remove({username: "smith"})
db.users.find({username: "smith"})
```

### users 컬렉션의 모든 문서 삭제 및 확인
```javascript
db.users.remove({})
db.users.find()
```

### users 컬렉션 삭제
```javascript
db.users.drop()
```

---

## 2. 쿼리 작성 실습

### 문서 20,000건 추가
```javascript
use test

for(let i=0; i<20000; i++) {
  db.product.insert({
    num: i,
    name: '스마트폰' + i
  })
}
```

### 전체 문서 수 출력
```javascript
db.product.count()
```

### num 기준 내림차순 정렬
```javascript
db.product.find().sort({num: -1})
```

### num 기준 내림차순 상위 10건 출력
```javascript
db.product.find().sort({num: -1}).limit(10)
```

### num 기준 내림차순 6페이지(페이지당 10건) 출력
```javascript
db.product.find().sort({num: -1}).skip(10*(6-1)).limit(10)
```

### num이 15 미만 또는 19995 초과인 문서 출력
```javascript
db.product.find({
  $or: [
    {num: {$lt: 15}},
    {num: {$gt: 19995}}
  ]
})
```

### name이 '스마트폰10', '스마트폰100', '스마트폰1000' 중 하나인 문서 출력
```javascript
db.product.find({
  name: {$in: ['스마트폰10', '스마트폰100', '스마트폰1000']}
})
```

### num이 5 미만인 문서에서 name만 출력 (_id 제외)
```javascript
db.product.find(
  {num: {$lt: 5}},
  {_id: 0, name: 1}
)
```
