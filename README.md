# mileage service

## 사용 기술
* Java11
* Spring Boot 2.7.1
* Spring Data JPA
* lombok
* Swagger3
* Mysql
* Gradle
* Junit5


## 디렉터리 구조
MySQL의 계정 이름은 **root**, 계정 비밀번호는 **1234** 입니다. 

#### 1. 데이터베이스 생성

~~~sql
create database mileage character set utf8mb4 collate utf8mb4_general_ci;
~~~


#### 2. DDL
~~~mysql
create table user (
       uuid BINARY(16) not null,
        reg_time datetime(6),
        update_time datetime(6),
        primary key (uuid)
    ) engine=InnoDB;
    
create table place (
       uuid BINARY(16) not null,
        reg_time datetime(6),
        update_time datetime(6),
        primary key (uuid)
    ) engine=InnoDB;
    
create table point (
       id bigint not null,
        reg_time datetime(6),
        update_time datetime(6),
        point_count integer,
        user_uuid BINARY(16),
        primary key (id),
        foreign key (user_uuid) references user (uuid)
    ) engine=InnoDB;    
    
create table review (
       uuid BINARY(16) not null,
        reg_time datetime(6),
        update_time datetime(6),
        content varchar(255),
        place_id BINARY(16),
        user_id BINARY(16),
        primary key (uuid),
        foreign key (place_id) references place (uuid),
        foreign key (user_id) references user (uuid)
    ) engine=InnoDB;
    
    
create table image (
       uuid BINARY(16) not null,
        image_name varchar(255),
        image_url varchar(255),
        original_name varchar(255),
        review_uuid BINARY(16),
        primary key (uuid),
        foreign key (review_uuid) references review (uuid)
    ) engine=InnoDB;


    
create table point_history (
       id bigint not null,
        reg_time datetime(6),
        update_time datetime(6),
        action_type varchar(255),
        event_type varchar(255),
        point_count integer,
        review_id binary(255),
        point_id bigint,
        primary key (id),
        foreign key (point_id) references point (id)
    ) engine=InnoDB;
~~~

<br/>

## 폴더 구조

~~~
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── triple
    │   │           └── mileage
    │   │               ├── MileageApplication.java
    │   │               ├── domain
    │   │               │   ├── base
    │   │               │   │   └── BaseEntity.java
    │   │               │   ├── event
    │   │               │   │   ├── controller
    │   │               │   │   │   └── EventController.java
    │   │               │   │   ├── domain
    │   │               │   │   │   ├── ActionType.java
    │   │               │   │   │   └── EventType.java
    │   │               │   │   ├── dto
    │   │               │   │   │   └── EventRequestDto.java
    │   │               │   │   └── service
    │   │               │   │       └── EventService.java
    │   │               │   ├── image
    │   │               │   │   ├── domain
    │   │               │   │   │   └── Image.java
    │   │               │   │   ├── dto
    │   │               │   │   │   └── ImageDto.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── ImageRepository.java
    │   │               │   │   └── service
    │   │               │   │       ├── FileService.java
    │   │               │   │       └── ImageService.java
    │   │               │   ├── place
    │   │               │   │   ├── controller
    │   │               │   │   │   └── PlaceController.java
    │   │               │   │   ├── domain
    │   │               │   │   │   └── Place.java
    │   │               │   │   ├── dto
    │   │               │   │   │   └── PlaceDto.java
    │   │               │   │   ├── exception
    │   │               │   │   │   └── PlaceNotFoundException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── PlaceRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── PlaceService.java
    │   │               │   ├── point
    │   │               │   │   ├── controller
    │   │               │   │   │   └── PointController.java
    │   │               │   │   ├── domain
    │   │               │   │   │   └── Point.java
    │   │               │   │   ├── dto
    │   │               │   │   │   └── PointDto.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── PointRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── PointService.java
    │   │               │   ├── pointhistory
    │   │               │   │   ├── domain
    │   │               │   │   │   └── PointHistory.java
    │   │               │   │   ├── dto
    │   │               │   │   │   └── PointHistoryDto.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── PointHistoryRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── PointHistoryService.java
    │   │               │   ├── review
    │   │               │   │   ├── controller
    │   │               │   │   │   └── ReviewController.java
    │   │               │   │   ├── domain
    │   │               │   │   │   └── Review.java
    │   │               │   │   ├── dto
    │   │               │   │   │   ├── ReviewResponseDto.java
    │   │               │   │   │   ├── ReviewSaveDto.java
    │   │               │   │   │   └── ReviewSaveRequestDto.java
    │   │               │   │   ├── exception
    │   │               │   │   │   ├── ReviewNotFoundException.java
    │   │               │   │   │   └── ReviewWriteTwiceException.java
    │   │               │   │   ├── repository
    │   │               │   │   │   └── ReviewRepository.java
    │   │               │   │   └── service
    │   │               │   │       └── ReviewService.java
    │   │               │   └── user
    │   │               │       ├── controller
    │   │               │       │   └── UserController.java
    │   │               │       ├── domain
    │   │               │       │   └── User.java
    │   │               │       ├── dto
    │   │               │       │   └── UserDto.java
    │   │               │       ├── exception
    │   │               │       │   └── UserNotFoundException.java
    │   │               │       ├── repository
    │   │               │       │   └── UserRepository.java
    │   │               │       └── service
    │   │               │           └── UserService.java
    │   │               └── global
    │   │                   ├── config
    │   │                   │   └── SwaggerConfig.java
    │   │                   └── exception
    │   │                       ├── ErrorCode.java
    │   │                       ├── ErrorResponse.java
    │   │                       └── GlobalExceptionHandler.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── triple
                    └── mileage
                        ├── MileageApplicationTests.java
                        └── domain
                            ├── point
                            │   └── service
                            │       └── PointServiceTest.java
                            └── user
                                ├── repository
                                │   └── UserRepositoryTest.java
                                └── service
                                    └── UserServiceTest.java
~~~~

👉 처음엔 계층형으로 설계 하였다가 좀 더 간결하게 볼 수 있도록 DDD 구조로 변경하였습니다. <br/>
👉 프로젝트 전반적으로 사용되는 객체들은 global로 분리했고, exception등이 해당됩니다. <br/>

## 테이블 연관관계
![스크린샷 2022-07-12 오후 10 27 28](https://user-images.githubusercontent.com/40584381/178501043-4d62efa6-2038-4a28-912f-3242f946e6f0.png)

<br/>

### 스웨거 주소
http://localhost:8080/swagger-ui/index.html
해당 주소로 API 문서를 볼 수 있습니다.

<br/><br/>

## 시나리오 및 실행 방법 
각각 UserId, PlaceId, ReviewId, ImageId가 먼저 등록 된 후에 Event가 요청된다고 가정하였습니다. 각 ID는 UUID 형식입니다. <br/>
http://localhost:8080 주소에서 실행합니다.

### ⭐️ 1. User
* User를 먼저 추가해야 userId를 얻을 수 있습니다.
~~~
POST /users
GET /users/{userId} 
~~~
* UserId가 UUID 형식으로 생성됩니다.
* GET 요청으로 ID값을 확인할 수 있습니다.

### ⭐️ 2. Place
* Place를 먼저 추가해야 placeId를 얻을 수 있습니다.
~~~
POST /places
GET /places/{placeId}
~~~
* PostId가 UUID 형식으로 생성됩니다.
* GET 요청으로 ID값을 확인할 수 있습니다.

### ⭐️ 3. Review
~~~
POST /reviews
PUT /reviews/{reviewId}
DELETE /reviews/{reviewId}
~~~
* userId와 placeId, review등록에 필요한 content, reviewImage 파라미터 값으로 해당 값들을 보냅니다.
<br/>
(예시)

![스크린샷 2022-07-12 오후 10 40 00](https://user-images.githubusercontent.com/40584381/178503731-116227bb-e317-4471-896e-d7cec984aa76.png)


### ⭐️ 4. Event <br/>
이벤트는 리뷰를 등록/수정/삭제한 직후에 한 번 일어난다고 간주합니다. 그래야 등록된 ID와 이벤트가 제대로 들어온다고 생각하였습니다. <br/>
**이벤트의 요청으로 증감될 때마다 Point Table을 Update하여 현재 시점의 포인트를 조회할 수 있도록 하고, PointHistory Table에 증감 내역을 기록합니다.**

~~~
POST /events
~~~
* 위의 과정에서 얻어진 userId, reviewId, PlaceId, Review의 content, attachedPhotoIds(이미지의 UUID)들을 보냅니다.

#### 👉ADD
~~~java
public int calculateAddPoint(EventRequestDto eventRequestDto, Place place) {
        int point = 0;

        // 1자 이상 텍스트 작성
        if (eventRequestDto.getContent().length() > 0) {
            ++point;
        }

        // 1장 이상 사진 첨부
        if (eventRequestDto.getAttachedPhotoIds().size() > 0) {
            ++point;
        }

        //첫 리뷰 작성 1점
        if (reviewRepository.existsByPlace(place)) {
            ++point;
        }
        return point;
    }
~~~

#### 👉 MOD
~~~java
public int calculateModPoint(EventRequestDto eventRequestDto, Review review) {
        int point = 0;
        int savedImageCount = imageRepository.countByReview(review);
        int savedContentLength = review.getContent().length();
        int attachedPhotoCount = eventRequestDto.getAttachedPhotoIds().size();

        //작성된 내용을 지우면 -1
        if (savedContentLength > 0 && eventRequestDto.getContent().length() == 0) {
            --point;
        }

        //작성된 내용이 추가되면 +1
        if (savedContentLength == 0 && eventRequestDto.getContent().length() > 0) {
            ++point;
        }

        //사진을 등록하지 않았다가 추가로 등록하는 경우 +1
        if (savedImageCount == 0 && attachedPhotoCount > 0) {
            ++point;
        }

        //사진을 등록했다가 지우는 경우 -1
        if (savedImageCount > 0 && attachedPhotoCount == 0) {
            --point;
        }
        return point;
    }
~~~

#### 👉 DELETE
~~~java
public int calculateDeletePoint(UUID reviewId) {
        //기존 리뷰에서 얼만큼 얻었나 확인
        return pointHistoryRepository.countByReviewId(reviewId);
    }
~~~

⭐️ 5. Point 조회
* 사용자의 현재 포인트를 조회할 수 있습니다.
~~~
GET /points/{userId}
~~~


### 체크리스트

- [x] 포인트 증감이 있을 때마다 이력 남기기
- [x] 리뷰를 작성했다가 삭제하면 해당 리뷰로 부여한 내용 점수와 보너스 점수 회수하기
- [x] 리뷰를 수정하면 수정한 내용에 맞는 내용 점수를 계산해 점수를 부여하거나 회수하기
  * 글만 작성한 리뷰에 사진 추가하면 1점 부여
  * 글과 사진이 있는 리뷰에서 사진을 모두 삭제하면 1점 회수
  * 🧐 추가적으로 내용에 대한 조건으로, 내용이 1자 이상 작성되었다가 0자 이하면 -1, 내용이 입력되지 않았다가 1자 이상으로 입력되면 +1를 부여하도록 하였습니다.
- [x] 사용자 입장에서 본 '첫 리뷰'일 때 보너스 점수 부여하기
- [x] 사용자마다 현재 시점의 포인트 총점 조회하기 : Point Table

<br/>

## 예외 처리
Exception
- 마일리지 서비스 전반적으로 적용됩니다.
- ErrorCode를 enum으로 에러코드, 상태코드, 메시지를 설정하여 재사용 가능하도록 하였습니다.
- 핸들러를 통해서 Exception을 전반적으로 관리하였고, 예외 발생하면 해당 Exception에 따라 Response해주었습니다.

<br/>

## 고민했던 점
객체지향적인 관점에서 각자의 역할이 무엇인지 분배하기 위해 고민을 많이 했습니다. 이에 따라 각자의 관심사와 역할을 적절히 분리하고 지정하고자 노력하였습니다.

<br/>

## 트러블 슈팅
UUID로 Find가 되지 않는 문제를 겪었습니다.
Hibernate가 설정한 데이터 타입은 binary(255)였고, UUID의 값은 binary(16)이기 때문에 mysql의 빈 공간을 padding으로 0을 채우게 되어 나오지 않는 것이었습니다.
~~~java
@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(
        name = "UUID",
         trategy = "org.hibernate.id.UUIDGenerator"
)
@Column(columnDefinition = "BINARY(16)") // 이렇게 BINARY(16)으로 지정하여 문제를 해결하였습니다.
public UUID uuid;
~~~



