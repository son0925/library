INSERT INTO users (id, password, role, name, phone, memo, status, create_at) VALUES
(1, 'admin123!', 'ADMIN', '관리자', '010-1111-1111', '도서관 관리자', 'ACTIVE', CURRENT_TIMESTAMP),
(2, 'user1234', 'USER', '홍길동', '010-2222-2222', '일반 유저 1', 'ACTIVE', CURRENT_TIMESTAMP),
(3, 'user5678', 'USER', '김영희', '010-3333-3333', '일반 유저 2', 'ACTIVE', CURRENT_TIMESTAMP);



INSERT INTO book (title, description, author, publisher, publication_at, image_url, price, category)
VALUES
('자바의 정석', '자바 언어의 기초부터 고급까지 설명', '남궁성', '도우출판', '2016-03-10 00:00:00', 'https://example.com/java.jpg', 35000, 'IT'),
('이펙티브 자바', '자바 프로그래밍 베스트 프랙티스', '조슈아 블로크', '인사이트', '2018-01-11 00:00:00', 'https://example.com/effective.jpg', 42000, 'IT'),
('클린 코드', '가독성과 유지보수성이 좋은 코드 작성을 위한 지침', '로버트 마틴', '인사이트', '2013-07-01 00:00:00', 'https://example.com/clean.jpg', 38000, 'IT'),
('토비의 스프링', '스프링 철학과 실전 구성 원리', '이일민', '에이콘출판', '2014-09-05 00:00:00', 'https://example.com/toby.jpg', 48000, 'IT'),
('오브젝트', '객체지향의 본질과 설계 원칙', '조영호', '위키북스', '2019-09-10 00:00:00', 'https://example.com/object.jpg', 39000, 'IT'),
('데미안', '자아와 영혼의 성장에 대한 이야기', '헤르만 헤세', '민음사', '2009-05-01 00:00:00', 'https://example.com/demian.jpg', 13000, 'LITERATURE'),
('총, 균, 쇠', '문명의 발전 차이를 과학적으로 설명', '재레드 다이아몬드', '문학사상사', '2005-02-01 00:00:00', 'https://example.com/gun.jpg', 22000, 'HISTORY'),
('사피엔스', '인간의 진화와 문명의 시작', '유발 하라리', '김영사', '2015-11-15 00:00:00', 'https://example.com/sapiens.jpg', 23000, 'HISTORY'),
('코스모스', '우주의 신비를 풀어낸 과학 명저', '칼 세이건', '사이언스북스', '2001-04-12 00:00:00', 'https://example.com/cosmos.jpg', 25000, 'SCIENCE'),
('인간 본성에 대하여', '진화 심리학 관점에서 본 인간 이해', '에드워드 윌슨', '사이언스북스', '2002-03-10 00:00:00', 'https://example.com/human.jpg', 27000, 'SCIENCE'),
('자유론', '자유와 권리에 대한 철학적 탐구', '존 스튜어트 밀', '을유문화사', '2010-09-10 00:00:00', 'https://example.com/liberty.jpg', 15000, 'PHILOSOPHY'),
('니코마코스 윤리학', '윤리에 대한 아리스토텔레스의 명저', '아리스토텔레스', '아카넷', '2008-02-20 00:00:00', 'https://example.com/nico.jpg', 18000, 'PHILOSOPHY'),
('부자 아빠 가난한 아빠', '자산과 부의 개념을 바꿔주는 책', '로버트 기요사키', '민음인', '2004-01-01 00:00:00', 'https://example.com/rich.jpg', 20000, 'ECONOMY'),
('경제학 콘서트', '경제학을 재미있게 설명한 입문서', '팀 하포드', '웅진지식하우스', '2011-06-15 00:00:00', 'https://example.com/econ.jpg', 17000, 'ECONOMY'),
('아침형 인간', '성공을 위한 습관 형성 가이드', '할 엘로드', '한빛비즈', '2017-03-01 00:00:00', 'https://example.com/morning.jpg', 16000, 'SELF_DEVELOPMENT'),
('몰입', '몰입 상태에서의 창의성과 성취', '황농문', '랜덤하우스코리아', '2007-04-20 00:00:00', 'https://example.com/focus.jpg', 19000, 'SELF_DEVELOPMENT'),
('미술관에 간 화학자', '화학으로 본 미술작품 이야기', '전창림', '어크로스', '2019-05-10 00:00:00', 'https://example.com/artchem.jpg', 21000, 'ART'),
('빈센트 반 고흐', '반 고흐의 예술과 삶', '이석우', '다빈치북스', '2021-06-15 00:00:00', 'https://example.com/van.jpg', 25000, 'ART'),
('나는 걸어서 세계를 읽는다', '세계 각지를 여행하며 쓴 인문 에세이', '한비야', '푸른숲', '2010-10-10 00:00:00', 'https://example.com/travel.jpg', 17000, 'TRAVEL'),
('서울대에서는 이런 책을 읽는다', '교육의 본질과 독서법에 대한 제안', '정찬용', '명진출판', '2013-07-01 00:00:00', 'https://example.com/seoul.jpg', 16000, 'EDUCATION');
