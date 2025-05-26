1-1. 데이터베이스를 tutoturial로 선정
use tutorial

1-2.users 컬렉션에 username이 smith인 도큐먼트 저장
db.users.insert({username : "smith"})

1-3.users 컬렉션에 username이 jones인 도큐먼트 저장
db.users.insert({username : "jones"})

1-4. 앞에서 저장한 모든 문서 출력
db.users.find()

1-5. 앞에서 저장한 문서 중 하나만 출력
db.users.find({_id:0}) (이건 틀림)
db.users.findOne()

1-6. users 컬렉션에서 username이 "jones"인 문서 찾아서 출력
db.users.find({username: "jones"})

1-7. users컬렉션에서 username이 "jones" 또는 "smith"인 문서 찾아 출력
db.users.find({$or:[{username : "smith"}, {username : "jones"}]})

1-8. users 컬렉션에서 username 이 smith인 문서에 counrty키가 Canada가 되도록 수정
db.users.update({username : "smith"}, {$set : {country : "Canada"}})

1-9. 앞의 문서 올바르게 수정되었는지 확인
db.users.find({username : "smith"})

1-10. users 컬렉션에서 username이 smith인 문서를 {counrty:"Canada"}로 대체, 확인
db.users.update({username: "smith"}, {country: "Canada"}) -> 오류남 
이 명령어는 두 번째 인자인 업데이트 문서에 업데이트 연산자(atomic operator) ($set, $inc 등)를 사용하지 않았기 때문에 오류가 발생합니다.
db.users.find({country: "Canada"})

1-11.  users 컬렉션에서 country가 Canada인 문서를{username: "smith", country: "Canada"}로 대체하고확인하세요.
db.users.update({country: "Canada"}, {$set: {username: "smith", country: "Canada"}})
db.users.find({username: "smith"})

1-12.users 컬렉션에서 username이 smith인 문서에서 country 키를 삭제하고, 삭제 여부를 확인
db.users.update({username: "smith"}, {$unset: {country: 1}})
db.users.find({username : "smith"})

2-1. 데이터베이스 전체 목록 출력
show dbs

2-2. 현재 사용 중인 데이터베이스 컬렉션 목록 출력
show collections

2-3. 현재 사용 중인 데이터베이스, users컬렉션 상태 출력
db.status()
db.stats()
db.users.stats()


2-4번 users 컬렉션에서 username이 smith인 문서를 삭제하고, 삭제 여부를 확인하세요.
db.users.remove({username : "smith"})
 db.users.find({username: "smith"})

2-5번 users 컬렉션의 모든 문서를 삭제하고, 삭제 여부를 확인하세요.
db.users.remove({})
db.users.find()

2-6번 users 컬렉션을 삭제하세요.
db.users.drop()

3-1. 다음 형태의 문서 20,000건을 추가하세요.
이미지 
use test

3-2. product 컬렉션의 전체 문서 수를 출력하세요
db.product.count()

3-3. 다음 형태의 문서 20,000건을 추가하세요.
###  ○ database: test
### ○ collection: product
### ○ 문서필드
###  num: 순번(0부터시작)
###  name: '스마트폰' + 순번  
 
for(let i = 0; i<20000; i++){  
db.product.insert({ num: i, name: '스마트폰' + i });}  

3-4  product 컬렉션의 전체 문서 수를 출력하세요.  
db.product.count()  

3-5. product 컬렉션의 문서를 num의 내림차순으로 정렬하여 출력하세요.  
db.product.find().sort({num : -1});  / 내림차순
db.product.find().sort({num : 1});   / 오름차순

3-6.product 컬렉션의 문서를 num의 내림차순으로 정렬하여 상위 10건을 출력하세요.  
 db.product.find().sort({num-1}).limit(10); / 내림차순 상위 10건
 db.product.find().sort({num1}).limit(10); / 오름차순 상위 10건

3-7.  product 컬렉션의 문서를 num의 내림차순으로 정렬한 상태에서 다음을 처리하세요.  
db.product.find()
.sort(({num-1})
.skip(10*(6-1))
.limit(10);

3-8.product 컬렉션에서 num이 15미만이거나 19995 초과인 것을 출력하세요.  
 
 db.product.find({ $or:
 [
 {num: {$lt: 15}},
 {num: {$gt: 19995}},
 ]
 });

3-9. product 컬렉션에서name이'스마트폰10', '스마트폰100', '스마트폰 1000' 중에 하나이면출력
db.product.find({ name:
 {$in: ['스마트폰10', '스마트폰100', '스마트폰1000']}
 })

3-10.product 컬렉션에서 num이 5보다 작은 문서를 출력하는데, name만 출력하세요. (_id 출력하면 안됨)
 db.product.find({num: {$lt: 5}}, {_id:0, name:1})



