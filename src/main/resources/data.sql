
INSERT INTO groupworks.department (department_id, contact_number, department_name, location)
VALUES (1, '010-1234-5678', '기술부서', 'A동'),
       (2, '010-1234-5679', '마케팅부서', 'B동'),
       (3, '010-1234-5680', '영업부서', 'C동'),
       (4, '010-1234-5681', '인사부서', 'D동'),
       (5, '010-1234-5682', '재무부서', 'E동'),
       (6, '010-1234-5683', 'IT 지원부서', 'F동'),
       (7, '010-1234-5684', '제품 관리부서', 'G동'),
       (8, '010-1234-5685', '고객 서비스부서', 'H동'),
       (9, '010-1234-5686', '법무부서', 'I동'),
       (10, '010-1234-5687', '연구개발부서', 'J동');


INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',1,NULL,50000,0,'주소 1','ljm@gw.com','이정민','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5600','부장'),
	 (1,4,'2022-01-01 00:00:00',1,NULL,45000,1,'주소 2','kah@gw.com','김아현','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5601','차장'),
	 (1,3,'2023-01-01 00:00:00',1,NULL,40000,2,'주소 3','yhj@gw.com','유현종','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5602','과장'),
	 (1,3,'2023-01-01 00:00:00',1,NULL,40000,2,'주소 4','cis@gw.com','최일성','$2a$10$.Qq.aejC3HrIcjswI79HpOuMXiy9Yc3eoUiuabYOV3Lo2CdGty2CO','남','010-1234-5603','과장'),
	 (1,2,'2023-06-01 00:00:00',1,NULL,35000,4,'주소 5','kss@gw.com','김신실','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5604','대리'),
	 (1,2,'2023-06-01 00:00:00',1,NULL,35000,4,'주소 6','ksy@gw.com','김소연','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5605','대리'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30000,6,'주소 7','smj@gw.com','소민지','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5606','사원'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30000,6,'주소 8','mjy@gw.com','문주영','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5607','사원'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30000,6,'주소 9','mjh@gw.com','마정하','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5608','사원'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30000,6,'주소 10','pcy@gw.com','박채윤','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5609','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',2,NULL,30100,0,'주소 11','employee11@gw.com','사원11','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5610','부장'),
	 (1,4,'2022-01-01 00:00:00',2,NULL,30560,56,'주소 12','employee12@gw.com','사원12','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5611','차장'),
	 (1,3,'2023-01-01 00:00:00',2,NULL,30120,12,'주소 13','employee13@gw.com','사원13','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5612','과장'),
	 (1,3,'2023-01-01 00:00:00',2,NULL,30130,12,'주소 14','employee14@gw.com','사원14','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5613','과장'),
	 (1,2,'2023-06-01 00:00:00',2,NULL,30140,14,'주소 15','employee15@gw.com','사원15','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5614','대리'),
	 (1,2,'2023-06-01 00:00:00',2,NULL,30150,14,'주소 16','employee16@gw.com','사원16','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5615','대리'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30160,16,'주소 17','employee17@gw.com','사원17','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5616','사원'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30170,16,'주소 18','employee18@gw.com','사원18','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5617','사원'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30180,16,'주소 19','employee19@gw.com','사원19','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5618','사원'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30190,16,'주소 20','employee20@gw.com','사원20','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5619','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',3,NULL,30200,0,'주소 21','employee21@gw.com','사원21','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5620','부장'),
	 (1,4,'2022-01-01 00:00:00',3,NULL,30210,21,'주소 22','employee22@gw.com','사원22','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5621','차장'),
	 (1,3,'2023-01-01 00:00:00',3,NULL,30220,22,'주소 23','employee23@gw.com','사원23','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5622','과장'),
	 (1,3,'2023-01-01 00:00:00',3,NULL,30230,22,'주소 24','employee24@gw.com','사원24','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5623','과장'),
	 (1,2,'2023-06-01 00:00:00',3,NULL,30240,24,'주소 25','employee25@gw.com','사원25','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5624','대리'),
	 (1,2,'2023-06-01 00:00:00',3,NULL,30250,24,'주소 26','employee26@gw.com','사원26','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5625','대리'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30260,26,'주소 27','employee27@gw.com','사원27','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5626','사원'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30270,26,'주소 28','employee28@gw.com','사원28','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5627','사원'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30280,26,'주소 29','employee29@gw.com','사원29','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5628','사원'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30290,26,'주소 30','employee30@gw.com','사원30','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5629','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',4,NULL,30300,0,'주소 31','employee31@gw.com','사원31','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5630','부장'),
	 (1,4,'2022-01-01 00:00:00',4,NULL,30310,31,'주소 32','employee32@gw.com','사원32','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5631','차장'),
	 (1,3,'2023-01-01 00:00:00',4,NULL,30320,32,'주소 33','employee33@gw.com','사원33','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5632','과장'),
	 (1,3,'2023-01-01 00:00:00',4,NULL,30330,32,'주소 34','employee34@gw.com','사원34','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5633','과장'),
	 (1,2,'2023-06-01 00:00:00',4,NULL,30340,34,'주소 35','employee35@gw.com','사원35','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5634','대리'),
	 (1,2,'2023-06-01 00:00:00',4,NULL,30350,34,'주소 36','employee36@gw.com','사원36','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5635','대리'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30360,36,'주소 37','employee37@gw.com','사원37','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5636','사원'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30370,36,'주소 38','employee38@gw.com','사원38','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5637','사원'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30380,36,'주소 39','employee39@gw.com','사원39','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5638','사원'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30390,36,'주소 40','employee40@gw.com','사원40','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5639','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',5,NULL,30400,0,'주소 41','employee41@gw.com','사원41','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5640','부장'),
	 (1,4,'2022-01-01 00:00:00',5,NULL,30410,41,'주소 42','employee42@gw.com','사원42','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5641','차장'),
	 (1,3,'2023-01-01 00:00:00',5,NULL,30420,42,'주소 43','employee43@gw.com','사원43','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5642','과장'),
	 (1,3,'2023-01-01 00:00:00',5,NULL,30430,42,'주소 44','employee44@gw.com','사원44','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5643','과장'),
	 (1,2,'2023-06-01 00:00:00',5,NULL,30440,44,'주소 45','employee45@gw.com','사원45','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5644','대리'),
	 (1,2,'2023-06-01 00:00:00',5,NULL,30450,44,'주소 46','employee46@gw.com','사원46','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5645','대리'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30460,46,'주소 47','employee47@gw.com','사원47','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5646','사원'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30470,46,'주소 48','employee48@gw.com','사원48','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5647','사원'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30480,46,'주소 49','employee49@gw.com','사원49','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5648','사원'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30490,46,'주소 50','employee50@gw.com','사원50','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5649','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',6,NULL,30500,0,'주소 51','employee51@gw.com','사원51','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5650','부장'),
	 (1,4,'2022-01-01 00:00:00',6,NULL,30510,51,'주소 52','employee52@gw.com','사원52','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5651','차장'),
	 (1,3,'2023-01-01 00:00:00',6,NULL,30520,52,'주소 53','employee53@gw.com','사원53','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5652','과장'),
	 (1,3,'2023-01-01 00:00:00',6,NULL,30530,52,'주소 54','employee54@gw.com','사원54','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5653','과장'),
	 (1,2,'2023-06-01 00:00:00',6,NULL,30540,54,'주소 55','employee55@gw.com','사원55','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5654','대리'),
	 (1,2,'2023-06-01 00:00:00',6,NULL,30550,54,'주소 56','employee56@gw.com','사원56','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5655','대리'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30560,56,'주소 57','employee57@gw.com','사원57','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5656','사원'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30570,56,'주소 58','employee58@gw.com','사원58','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5657','사원'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30580,56,'주소 59','employee59@gw.com','사원59','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5658','사원'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30590,56,'주소 60','employee60@gw.com','사원60','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5659','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',7,NULL,30600,0,'주소 61','employee61@gw.com','사원61','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5660','부장'),
	 (1,4,'2022-01-01 00:00:00',7,NULL,30610,61,'주소 62','employee62@gw.com','사원62','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5661','차장'),
	 (1,3,'2023-01-01 00:00:00',7,NULL,30620,62,'주소 63','employee63@gw.com','사원63','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5662','과장'),
	 (1,3,'2023-01-01 00:00:00',7,NULL,30630,62,'주소 64','employee64@gw.com','사원64','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5663','과장'),
	 (1,2,'2023-06-01 00:00:00',7,NULL,30640,64,'주소 65','employee65@gw.com','사원65','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5664','대리'),
	 (1,2,'2023-06-01 00:00:00',7,NULL,30650,64,'주소 66','employee66@gw.com','사원66','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5665','대리'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30660,66,'주소 67','employee67@gw.com','사원67','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5666','사원'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30670,66,'주소 68','employee68@gw.com','사원68','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5667','사원'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30680,66,'주소 69','employee69@gw.com','사원69','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5668','사원'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30690,66,'주소 70','employee70@gw.com','사원70','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5669','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',8,NULL,30700,0,'주소 71','employee71@gw.com','사원71','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5670','부장'),
	 (1,4,'2022-01-01 00:00:00',8,NULL,30710,71,'주소 72','employee72@gw.com','사원72','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5671','차장'),
	 (1,3,'2023-01-01 00:00:00',8,NULL,30720,72,'주소 73','employee73@gw.com','사원73','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5672','과장'),
	 (1,3,'2023-01-01 00:00:00',8,NULL,30730,72,'주소 74','employee74@gw.com','사원74','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5673','과장'),
	 (1,2,'2023-06-01 00:00:00',8,NULL,30740,74,'주소 75','employee75@gw.com','사원75','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5674','대리'),
	 (1,2,'2023-06-01 00:00:00',8,NULL,30750,74,'주소 76','employee76@gw.com','사원76','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5675','대리'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30760,76,'주소 77','employee77@gw.com','사원77','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5676','사원'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30770,76,'주소 78','employee78@gw.com','사원78','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5677','사원'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30780,76,'주소 79','employee79@gw.com','사원79','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5678','사원'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30790,76,'주소 80','employee80@gw.com','사원80','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5679','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',9,NULL,30800,0,'주소 81','employee81@gw.com','사원81','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5680','부장'),
	 (1,4,'2022-01-01 00:00:00',9,NULL,30810,81,'주소 82','employee82@gw.com','사원82','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5681','차장'),
	 (1,3,'2023-01-01 00:00:00',9,NULL,30820,82,'주소 83','employee83@gw.com','사원83','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5682','과장'),
	 (1,3,'2023-01-01 00:00:00',9,NULL,30830,82,'주소 84','employee84@gw.com','사원84','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5683','과장'),
	 (1,2,'2023-06-01 00:00:00',9,NULL,30840,84,'주소 85','employee85@gw.com','사원85','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5684','대리'),
	 (1,2,'2023-06-01 00:00:00',9,NULL,30850,84,'주소 86','employee86@gw.com','사원86','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5685','대리'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30860,86,'주소 87','employee87@gw.com','사원87','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5686','사원'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30870,86,'주소 88','employee88@gw.com','사원88','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5687','사원'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30880,86,'주소 89','employee89@gw.com','사원89','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5688','사원'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30890,86,'주소 90','employee90@gw.com','사원90','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5689','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',10,NULL,30900,0,'주소 91','employee91@gw.com','사원91','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5690','부장'),
	 (1,4,'2022-01-01 00:00:00',10,NULL,30910,91,'주소 92','employee92@gw.com','사원92','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5691','차장'),
	 (1,3,'2023-01-01 00:00:00',10,NULL,30920,92,'주소 93','employee93@gw.com','사원93','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5692','과장'),
	 (1,3,'2023-01-01 00:00:00',10,NULL,30930,92,'주소 94','employee94@gw.com','사원94','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5693','과장'),
	 (1,2,'2023-06-01 00:00:00',10,NULL,30940,94,'주소 95','employee95@gw.com','사원95','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5694','대리'),
	 (1,2,'2023-06-01 00:00:00',10,NULL,30950,94,'주소 96','employee96@gw.com','사원96','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5695','대리'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30960,96,'주소 97','employee97@gw.com','사원97','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5696','사원'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30970,96,'주소 98','employee98@gw.com','사원98','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5697','사원'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30980,96,'주소 99','employee99@gw.com','사원99','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1234-5698','사원'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30990,96,'주소 100','employee100@gw.com','사원100','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1234-5699','사원');


-- Department 1: IT 관련 직무
UPDATE groupworks.employee
SET role = CASE
                    WHEN rank_id = 5 THEN 'MANAGER'
                    WHEN rank_id = 4 THEN 'SENIOR'
                    WHEN rank_id = 3 THEN 'JUNIOR'
                    ELSE 'ASSOCIATE'
    END;



INSERT INTO groupworks.vacation_history (annual_days_used, sick_days_used, other_days_used, total_annual, employee_id)
VALUES (0, 0, 0, 25, 1),
       (0, 0, 0, 25, 2),
       (0, 0, 0, 25, 3),
       (0, 0, 0, 25, 4),
       (0, 0, 0, 25, 5),
       (0, 0, 0, 25, 6),
       (0, 0, 0, 25, 7),
       (0, 0, 0, 25, 8),
       (0, 0, 0, 25, 9),
       (0, 0, 0, 25, 10),
       (0, 0, 0, 25, 11),
       (0, 0, 0, 25, 12),
       (0, 0, 0, 25, 13),
       (0, 0, 0, 25, 14),
       (0, 0, 0, 25, 15),
       (0, 0, 0, 25, 16),
       (0, 0, 0, 25, 17),
       (0, 0, 0, 25, 18),
       (0, 0, 0, 25, 19),
       (0, 0, 0, 25, 20),
       (0, 0, 0, 25, 21),
       (0, 0, 0, 25, 22),
       (0, 0, 0, 25, 23),
       (0, 0, 0, 25, 24),
       (0, 0, 0, 25, 25),
       (0, 0, 0, 25, 26),
       (0, 0, 0, 25, 27),
       (0, 0, 0, 25, 28),
       (0, 0, 0, 25, 29),
       (0, 0, 0, 25, 30),
       (0, 0, 0, 25, 31),
       (0, 0, 0, 25, 32),
       (0, 0, 0, 25, 33),
       (0, 0, 0, 25, 34),
       (0, 0, 0, 25, 35),
       (0, 0, 0, 25, 36),
       (0, 0, 0, 25, 37),
       (0, 0, 0, 25, 38),
       (0, 0, 0, 25, 39),
       (0, 0, 0, 25, 40),
       (0, 0, 0, 25, 41),
       (0, 0, 0, 25, 42),
       (0, 0, 0, 25, 43),
       (0, 0, 0, 25, 44),
       (0, 0, 0, 25, 45),
       (0, 0, 0, 25, 46),
       (0, 0, 0, 25, 47),
       (0, 0, 0, 25, 48),
       (0, 0, 0, 25, 49),
       (0, 0, 0, 25, 50),
       (0, 0, 0, 25, 51),
       (0, 0, 0, 25, 52),
       (0, 0, 0, 25, 53),
       (0, 0, 0, 25, 54),
       (0, 0, 0, 25, 55),
       (0, 0, 0, 25, 56),
       (0, 0, 0, 25, 57),
       (0, 0, 0, 25, 58),
       (0, 0, 0, 25, 59),
       (0, 0, 0, 25, 60),
       (0, 0, 0, 25, 61),
       (0, 0, 0, 25, 62),
       (0, 0, 0, 25, 63),
       (0, 0, 0, 25, 64),
       (0, 0, 0, 25, 65),
       (0, 0, 0, 25, 66),
       (0, 0, 0, 25, 67),
       (0, 0, 0, 25, 68),
       (0, 0, 0, 25, 69),
       (0, 0, 0, 25, 70),
       (0, 0, 0, 25, 71),
       (0, 0, 0, 25, 72),
       (0, 0, 0, 25, 73),
       (0, 0, 0, 25, 74),
       (0, 0, 0, 25, 75),
       (0, 0, 0, 25, 76),
       (0, 0, 0, 25, 77),
       (0, 0, 0, 25, 78),
       (0, 0, 0, 25, 79),
       (0, 0, 0, 25, 80),
       (0, 0, 0, 25, 81),
       (0, 0, 0, 25, 82),
       (0, 0, 0, 25, 83),
       (0, 0, 0, 25, 84),
       (0, 0, 0, 25, 85),
       (0, 0, 0, 25, 86),
       (0, 0, 0, 25, 87),
       (0, 0, 0, 25, 88),
       (0, 0, 0, 25, 89),
       (0, 0, 0, 25, 90),
       (0, 0, 0, 25, 91),
       (0, 0, 0, 25, 92),
       (0, 0, 0, 25, 93),
       (0, 0, 0, 25, 94),
       (0, 0, 0, 25, 95),
       (0, 0, 0, 25, 96),
       (0, 0, 0, 25, 97),
       (0, 0, 0, 25, 98),
       (0, 0, 0, 25, 99),
       (0, 0, 0, 25, 100);



INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 123번지 45층', '중앙HTA', '123-45-67900', '최기리', '010-5678-1234', '02-1234-5678', 'IT 교육, 국비훈련', '교육 서비스업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 이천시 이천동 2000번지 2000-1', '◎◎제약™', '123-45-67890', '홍길동', '010-1234-1325', '031-123-1325', '유제품', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 123', '◇◇식품', '124-45-67891', '김영희', '010-5678-1325', '02-567-1325', '음료수', '식품업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('부산광역시 해운대구 우동 1234', '☆☆유통', '125-45-67892', '이철수', '010-8765-4321', '051-876-4321', '가전제품', '유통업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('대구광역시 달서구 상인동 987', '▲▲무역', '126-45-67893', '박영수', '010-4567-7890', '053-456-7890', '자동차 부품', '무역업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('울산광역시 남구 삼산동 456', '♤♤화학', '127-45-67894', '최지우', '010-2345-6789', '052-234-6789', '화학물질', '화학업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('전라북도 전주시 완산구 서노송동 789', '◆◆농산', '128-45-67895', '김민수', '010-3456-7891', '063-345-7891', '농산물', '농업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경상남도 창원시 마산합포구 중앙동 123', '◇◇기계', '129-45-67896', '이성민', '010-4567-8912', '055-456-8912', '기계부품', '기계업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 수원시 팔달구 매산로 456', '○○전자', '130-45-67897', '박현준', '010-5678-9123', '031-567-9123', '전자제품', '전자업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 마포구 서교동 789', '△△의료', '131-45-67898', '최예진', '010-6789-1234', '02-678-1234', '의료기기', '의료업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 123번지 45층', '중앙HTA', '123-45-67900', '최기리', '010-5678-1234', '02-1234-5678', 'IT 교육, 국비훈련', '교육 서비스업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('대전광역시 중구 은행동 123', '▣▣섬유', '132-45-67899', '홍석현', '010-7891-2345', '042-789-2345', '섬유제품', '섬유업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 서초동 123-45', '삼성전자', '123-45-67890', '이재용', '010-1234-5678', '02-123-4567', '전자제품', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 영등포구 여의도동 234-56', 'LG화학', '124-45-67891', '신학철', '010-2345-6789', '02-234-5678', '화학물질', '화학업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 서초구 양재동 345-67', '현대자동차', '125-45-67892', '정의선', '010-3456-7890', '02-345-6789', '자동차', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 성남시 분당구 불정로 6', '네이버', '126-45-67893', '최수연', '010-4567-8901', '031-456-7890', '인터넷 서비스', '서비스업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 성남시 분당구 대왕판교로 645번길 16', '카카오', '127-45-67894', '홍은택', '010-5678-9012', '031-567-8901', '모바일 서비스', '정보통신업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 용산구 한강대로 100', '아모레퍼시픽', '128-45-67895', '서경배', '010-6789-0123', '02-678-9012', '화장품', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 152', '하이트진로', '129-45-67896', '김인규', '010-7890-1234', '02-789-0123', '음료', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 이천시 부발읍 경충대로 2091', 'SK하이닉스', '130-45-67897', '이석희', '010-8901-2345', '031-890-1234', '반도체', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 중구 동호로 330', 'CJ제일제당', '131-45-67898', '강신호', '010-9012-3456', '02-901-2345', '식품', '제조업');
INSERT INTO xe.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강서구 하늘길 77', '대한항공', '132-45-67899', '우기홍', '010-0123-4567', '02-012-3456', '항공 서비스', '운송업');


INSERT INTO board (create_date, department_id, employee_id, update_date, content, ip_address, subject, title, board_type) VALUES
  (DATE_SUB(NOW(), INTERVAL 600 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 목표 달성 현황","level":3}},{"id":"2","type":"paragraph","data":{"text":"2024년 목표 달성 현황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 10% 증가","목표 2: 고객 만족도 90% 달성","목표 3: 신규 제품 출시"]}},{"id":"4","type":"paragraph","data":{"text":"현재 달성 상황은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출: 8% 증가","고객 만족도: 85%","신규 제품 출시: 완료"]}}],"version":"2.30.5"}', '192.168.0.1', 'general', '부서 목표 달성 현황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 590 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"고객 피드백 요약","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 고객 피드백을 요약하여 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["긍정적인 피드백: 서비스 품질에 대한 만족도 높음","부정적인 피드백: 배송 지연 문제"]}},{"id":"4","type":"paragraph","data":{"text":"향후 개선 방안은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["배송 프로세스 개선","고객 서비스 교육 강화"]}}],"version":"2.30.5"}', '192.168.0.2', 'suggestion', '고객 피드백 요약', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 580 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"시스템 오류 보고","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음은 최근 시스템 오류에 대한 보고입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["오류 1: 로그인 문제","오류 2: 데이터 백업 실패"]}},{"id":"4","type":"paragraph","data":{"text":"문제 해결을 위한 조치는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["로그인 문제: 시스템 점검 및 업데이트 진행","데이터 백업: 백업 스크립트 점검 및 재설정"]}}],"version":"2.30.5"}', '192.168.0.3', 'error', '시스템 오류 보고', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 570 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 회의 일정 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 팀 회의 일정과 관련된 사항을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["회의 일시: 2024년 8월 30일 10:00 AM","회의 장소: 본사 회의실","회의 안건: 프로젝트 진행 상황 점검"]}},{"id":"4","type":"paragraph","data":{"text":"참석 부탁드립니다."}}],"version":"2.30.5"}', '192.168.0.4', 'announcement', '팀 회의 일정 안내', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 560 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 변경 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음은 최신 정책 변경 사항입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 1: 연차 사용 규정 변경","정책 2: 출근 시간 조정"]}},{"id":"4","type":"paragraph","data":{"text":"정책 변경에 대한 세부 사항은 인사팀에 문의하시기 바랍니다."}}],"version":"2.30.5"}', '192.168.0.5', 'mandatory', '정책 변경 공지', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 550 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"프로젝트 완료 보고","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 완료된 프로젝트에 대한 보고서입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["프로젝트 1: ABC 시스템 구현 완료","프로젝트 2: 고객 포털 리뉴얼 완료"]}},{"id":"4","type":"paragraph","data":{"text":"향후 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["다음 프로젝트: DEF 시스템 개발","업데이트: 고객 피드백 반영"]}}],"version":"2.30.5"}', '192.168.0.6', 'general', '프로젝트 완료 보고', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 540 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀의 최근 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 15% 증가","성과 2: 고객 만족도 92% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"향후 목표는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["목표 1: 매출 20% 증가","목표 2: 고객 만족도 95% 달성"]}}],"version":"2.30.5"}', '192.168.0.7', 'suggestion', '팀 성과 리뷰', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 530 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"고객 요청 사항","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 고객 요청 사항을 정리하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["요청 사항 1: 기능 개선 요청","요청 사항 2: 서비스 확장 요청"]}},{"id":"4","type":"paragraph","data":{"text":"요청 사항에 대한 대응 방안은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["기능 개선: 개발팀에 요청","서비스 확장: 마케팅 팀과 협의"]}}],"version":"2.30.5"}', '192.168.0.8', 'error', '고객 요청 사항', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 520 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서의 최근 성과를 분석합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 110% 달성","성과 2: 고객 만족도 88%"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성과: 목표를 초과 달성하였으나, 고객 만족도는 미비함","고객 피드백 분석: 긍정적인 피드백이 대부분"]}}],"version":"2.30.5"}', '192.168.0.9', 'general', '부서 성과 분석', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 510 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"신규 프로젝트 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음은 신규 프로젝트 제안 사항입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["프로젝트 1: AI 기반 고객 지원 시스템 개발","프로젝트 2: 글로벌 시장 진출 전략 수립"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 피드백은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["AI 프로젝트: 개발팀 검토 후 진행 여부 결정","글로벌 전략: 시장 조사 후 계획 수립"]}}],"version":"2.30.5"}', '192.168.0.10', 'suggestion', '신규 프로젝트 제안', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 500 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"성공 사례 보고","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 성공적인 프로젝트 사례를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["사례 1: 신규 제품 출시 성공","사례 2: 주요 클라이언트 계약 체결"]}},{"id":"4","type":"paragraph","data":{"text":"성과 분석은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["제품 출시: 예상 매출 초과 달성","계약 체결: 장기 계약으로 인한 안정적 매출"]}}],"version":"2.30.5"}', '192.168.0.11', 'general', '성공 사례 보고', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 490 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"작업 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"현재 진행 중인 작업의 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["작업 1: 웹사이트 리뉴얼 - 진행률 70%","작업 2: 서버 업그레이드 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["웹사이트 리뉴얼: 30% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.12', 'announcement', '작업 진행 상황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 480 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀의 목표 달성 현황을 검토합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 연간 매출 20% 증가","목표 2: 고객 피드백 점수 90% 이상 유지"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 증가: 18% 달성","고객 피드백: 87%"]}}],"version":"2.30.5"}', '192.168.0.13', 'general', '팀 목표 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 470 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선을 위한 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 고객 피드백을 반영한 서비스 개선","제안 2: 최신 기술을 활용한 서비스 최적화"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 반영: 서비스 개선 계획 수립","기술 활용: 기술팀과 논의"]}}],"version":"2.30.5"}', '192.168.0.14', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 460 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 성과 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀의 최근 성과를 분석합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 고객 만족도 85%","성과 2: 매출 목표 95% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["고객 만족도: 서비스 개선 필요","매출 목표: 목표 달성 성공"]}}],"version":"2.30.5"}', '192.168.0.15', 'general', '팀 성과 분석', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 450 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"프로젝트 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"현재 진행 중인 프로젝트의 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["프로젝트 1: CRM 시스템 구축 - 진행률 80%","프로젝트 2: 신규 마케팅 캠페인 - 시작 단계"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업과 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["CRM 시스템: 20% 추가 작업 필요","마케팅 캠페인: 전략 수립 후 진행"]}}],"version":"2.30.5"}', '192.168.0.16', 'announcement', '프로젝트 진행 상황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 440 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"고객 요구 사항","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 고객 요구 사항을 정리하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["요구 사항 1: 기능 개선 요청","요구 사항 2: 서비스 품질 향상 요청"]}},{"id":"4","type":"paragraph","data":{"text":"요구 사항에 대한 대응 방안은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["기능 개선: 개발팀에 전달","서비스 품질 향상: 고객 서비스팀과 협의"]}}],"version":"2.30.5"}', '192.168.0.17', 'error', '고객 요구 사항', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 430 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 1: 업무 효율성 증가","정책 2: 고객 응대 절차 개선"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 효율성: 긍정적인 변화 관찰","고객 응대: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.18', 'mandatory', '정책 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 420 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 현황","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 진행 현황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["개선 사항 1: 사용자 인터페이스 개선","개선 사항 2: 고객 지원 프로세스 개선"]}},{"id":"4","type":"paragraph","data":{"text":"개선 작업 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["인터페이스 개선: 완료","고객 지원 프로세스: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.19', 'suggestion', '서비스 개선 현황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 410 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 연간 매출 15% 증가","목표 2: 고객 만족도 90% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 마케팅 전략 강화","고객 만족도: 서비스 품질 개선"]}}],"version":"2.30.5"}', '192.168.0.20', 'general', '팀 목표 설정', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 400 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 고객 관리 시스템 구축 - 진행률 75%","업무 2: 연간 매출 보고서 작성 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["고객 관리 시스템: 25% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.21', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 390 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 변경 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 변경 사항을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["변경 사항 1: 연차 사용 규정 수정","변경 사항 2: 출근 시간 조정"]}},{"id":"4","type":"paragraph","data":{"text":"변경된 정책에 대한 세부 사항은 인사팀에 문의해 주시기 바랍니다."}}],"version":"2.30.5"}', '192.168.0.22', 'mandatory', '정책 변경 안내', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 380 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 10% 증가","성과 2: 고객 만족도 89%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 증가: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.23', 'general', '팀 성과 리뷰', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 370 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 계획","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선을 위한 계획을 수립하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["계획 1: 고객 피드백 반영","계획 2: 직원 교육 강화"]}},{"id":"4","type":"paragraph","data":{"text":"계획에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 반영: 서비스 개선 계획 수립","직원 교육: 교육 프로그램 개발"]}}],"version":"2.30.5"}', '192.168.0.24', 'suggestion', '서비스 개선 계획', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 360 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 프로세스 점검","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 프로세스를 점검한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["점검 사항 1: 업무 흐름 최적화","점검 사항 2: 업무 지침 재정비"]}},{"id":"4","type":"paragraph","data":{"text":"점검 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 흐름: 최적화 완료","업무 지침: 재정비 필요"]}}],"version":"2.30.5"}', '192.168.0.25', 'announcement', '업무 프로세스 점검', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 350 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 달성 여부","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.26', 'error', '팀 목표 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 340 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 업무 효율성 증가","검토 결과 2: 고객 만족도 향상"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 효율성: 향상됨","고객 만족도: 향상됨"]}}],"version":"2.30.5"}', '192.168.0.27', 'general', '정기 검토 결과', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 330 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI 분석 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["KPI 1: 매출 성장률","KPI 2: 고객 만족도"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 달성","고객 만족도: 목표 미달"]}}],"version":"2.30.5"}', '192.168.0.28', 'general', '부서 KPI 분석', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 320 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 계획","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 계획을 수립하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["계획 1: 사용자 경험 향상","계획 2: 기술 지원 강화"]}},{"id":"4","type":"paragraph","data":{"text":"계획에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["경험 향상: 사용자 인터페이스 개선","기술 지원: 지원 팀 교육 강화"]}}],"version":"2.30.5"}', '192.168.0.29', 'suggestion', '서비스 개선 계획', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 310 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 90% 달성","성과 2: 고객 만족도 85%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 유지"]}}],"version":"2.30.5"}', '192.168.0.30', 'general', '부서 성과 리뷰', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 300 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 회의","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 회의 일정을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["회의 1: 다음 주 월요일 오전 10시","회의 2: 프로젝트 진행 상황 리뷰"]}}],"version":"2.30.5"}', '192.168.0.31', 'announcement', '정기 회의', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 290 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 현황","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 현황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["현황 1: 고객 피드백 반영 완료","현황 2: 기술 지원 강화 진행 중"]}},{"id":"4","type":"paragraph","data":{"text":"현황에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 반영: 완료","기술 지원: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.32', 'announcement', '서비스 개선 현황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 280 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 분석","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 달성","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.33', 'general', '팀 목표 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 270 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 프로세스 개선","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 프로세스 개선을 위한 계획을 수립하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["계획 1: 업무 흐름 최적화","계획 2: 업무 지침 재정비"]}},{"id":"4","type":"paragraph","data":{"text":"계획에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 흐름: 최적화 계획 수립","업무 지침: 재정비 중"]}}],"version":"2.30.5"}', '192.168.0.34', 'suggestion', '업무 프로세스 개선', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 260 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 달성","목표 2: 고객 만족도 90% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 마케팅 전략 강화","고객 만족도: 서비스 품질 개선"]}}],"version":"2.30.5"}', '192.168.0.35', 'general', '팀 목표 설정', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 250 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 1: 직원 복지 개선","정책 2: 업무 절차 간소화"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["복지 개선: 긍정적인 변화 관찰","절차 간소화: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.36', 'general', '정책 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 240 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 분석한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 성장률 8%","성과 2: 고객 만족도 85%"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.37', 'general', '부서 성과 분석', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 230 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 고객 피드백 수집 및 분석","제안 2: 서비스 개선 팀 구성"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 수집: 진행 중","서비스 개선 팀: 구성 완료"]}}],"version":"2.30.5"}', '192.168.0.38', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 220 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 100% 달성","목표 2: 고객 만족도 95% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 신제품 출시","고객 만족도: 고객 지원 강화"]}}],"version":"2.30.5"}', '192.168.0.39', 'general', '팀 목표 설정', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 210 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 업무 프로세스 개선","검토 결과 2: 고객 만족도 향상"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 프로세스 개선: 완료","고객 만족도: 향상됨"]}}],"version":"2.30.5"}', '192.168.0.40', 'general', '정기 검토 결과', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 200 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 시스템 구축 - 진행률 80%","업무 2: 기존 시스템 유지보수 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["시스템 구축: 20% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.41', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 190 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["KPI 1: 매출 성장률 7%","KPI 2: 고객 만족도 80%"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.42', 'general', '부서 KPI 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 180 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 프로세스 점검","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 프로세스 점검 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["점검 사항 1: 업무 흐름 분석","점검 사항 2: 업무 지침 검토"]}},{"id":"4","type":"paragraph","data":{"text":"점검 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 흐름: 분석 완료","업무 지침: 검토 중"]}}],"version":"2.30.5"}', '192.168.0.43', 'announcement', '업무 프로세스 점검', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 170 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 서비스 제공 방식 개선","제안 2: 고객 피드백 적극 반영"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 제공 방식: 개선 중","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.44', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 160 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 85% 달성","성과 2: 고객 만족도 82%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.45', 'general', '부서 성과 리뷰', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 150 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 변경 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 변경 사항을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["변경 사항 1: 복리후생 개선","변경 사항 2: 근무 시간 조정"]}},{"id":"4","type":"paragraph","data":{"text":"변경된 정책에 대한 세부 사항은 인사팀에 문의해 주시기 바랍니다."}}],"version":"2.30.5"}', '192.168.0.46', 'mandatory', '정책 변경 안내', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 140 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI를 분석한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["KPI 1: 매출 성장률 9%","KPI 2: 고객 만족도 83%"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.47', 'general', '부서 KPI 분석', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 130 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 달성 여부","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 달성","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.48', 'general', '팀 목표 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 120 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 시스템 개발 - 진행률 60%","업무 2: 기존 시스템 유지보수 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["신규 시스템 개발: 40% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.49', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 110 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 서비스 품질 개선","검토 결과 2: 고객 피드백 반영"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 품질: 개선됨","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.50', 'general', '정기 검토 결과', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 100 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 90% 달성","목표 2: 고객 만족도 88% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 판매 촉진 활동","고객 만족도: 고객 서비스 개선"]}}],"version":"2.30.5"}', '192.168.0.51', 'general', '팀 목표 설정', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 90 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 서비스 제공 방식 개선","제안 2: 고객 피드백 적극 반영"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 제공 방식: 개선 중","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.52', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 80 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI를 검토한 결과를 보고드립니다."}},{"id":"3","data":{"style":"unordered","items":["KPI 1: 매출 성장률 6%","KPI 2: 고객 만족도 78%"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.53', 'general', '부서 KPI 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 70 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 시스템 개발 - 진행률 50%","업무 2: 기존 시스템 유지보수 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["신규 시스템 개발: 50% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.54', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 60 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 분석","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 달성","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.55', 'general', '팀 목표 검토', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 50 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 서비스 품질 향상","검토 결과 2: 고객 피드백 반영"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 품질: 향상됨","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.56', 'general', '정기 검토 결과', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 40 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 95% 달성","성과 2: 고객 만족도 87%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 유지"]}}],"version":"2.30.5"}', '192.168.0.57', 'general', '부서 성과 리뷰', 'DEPARTMENT'),
  (DATE_SUB(NOW(), INTERVAL 30 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 100% 달성","목표 2: 고객 만족도 90% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 마케팅 강화","고객 만족도: 서비스 개선"]}}],"version":"2.30.5"}', '192.168.0.58', 'general', '팀 목표 설정', 'DEPARTMENT');


INSERT INTO board (create_date, department_id, employee_id, update_date, content, ip_address, subject, title, board_type) VALUES
    (DATE_SUB(NOW(), INTERVAL 600 MINUTE), null, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"사내 워크숍 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 주 금요일, 사내 워크숍이 진행됩니다. 모든 직원은 참석해주시기 바랍니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 30일 오전 10시","장소: 본사 3층 회의실","준비물: 필기구"]}}],"version":"2.30.5"}', '192.168.0.1', 'announcement', '사내 워크숍 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 550 MINUTE), null, 8, NULL, '{"time":1724201390947,"blocks":[{"id":"1","type":"header","data":{"text":"부서 점심 모임","level":3}},{"id":"2","type":"paragraph","data":{"text":"이번 주 목요일, 12시에 부서 점심 모임이 있습니다. 참석 여부를 알려주시기 바랍니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 23일 오후 12시","장소: 사내 카페테리아","특이사항: 알레르기 정보 제공"]}}],"version":"2.30.5"}', '192.168.0.2', 'announcement', '부서 점심 모임 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 500 MINUTE), null, 7, NULL, '{"time":1724201390948,"blocks":[{"id":"1","type":"header","data":{"text":"연말 파티 일정","level":3}},{"id":"2","type":"paragraph","data":{"text":"연말 파티가 12월 20일 저녁 7시에 진행됩니다. 연말 분위기를 만끽할 수 있는 자리가 될 것입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 12월 20일 오후 7시","장소: 본사 1층 대연회장","복장: 정장"]}}],"version":"2.30.5"}', '192.168.0.3', 'announcement', '연말 파티 일정 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 450 MINUTE), null, 6, NULL, '{"time":1724201390949,"blocks":[{"id":"1","type":"header","data":{"text":"업무 시스템 점검 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"시스템 점검이 8월 25일 일요일 진행됩니다. 점검 시간 동안 시스템 사용이 제한됩니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["점검일시: 2024년 8월 25일 오전 2시 - 오전 6시","영향: 전사 시스템 사용 불가","대책: 업무 중인 경우 미리 작업 저장"]}}],"version":"2.30.5"}', '192.168.0.4', 'announcement', '업무 시스템 점검 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 400 MINUTE), null, 5, NULL, '{"time":1724201390950,"blocks":[{"id":"1","type":"header","data":{"text":"신규 복지 정책 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"새로운 복지 정책이 도입됩니다. 자세한 내용은 첨부된 문서를 참조해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 내용: 건강 검진 지원, 체력 단련비 지원","적용일: 2024년 9월 1일","문의: 인사팀"]}}],"version":"2.30.5"}', '192.168.0.5', 'announcement', '신규 복지 정책 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 350 MINUTE), null, 4, NULL, '{"time":1724201390951,"blocks":[{"id":"1","type":"header","data":{"text":"사내 장비 교체 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"오는 9월 1일부터 사내 장비 교체 작업이 진행됩니다. 업무에 참고해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["교체 대상: 노트북, 데스크탑","교체 일정: 2024년 9월 1일 - 9월 5일","영향: 일부 부서의 장비 사용 제한"]}}],"version":"2.30.5"}', '192.168.0.6', 'announcement', '사내 장비 교체 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 300 MINUTE), null, 3, NULL, '{"time":1724201390952,"blocks":[{"id":"1","type":"header","data":{"text":"건강 세미나 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"건강 세미나가 9월 10일에 진행됩니다. 건강 관리에 대한 유익한 정보를 제공할 예정입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 10일 오후 2시","장소: 본사 2층 세미나실","강사: 건강 전문가 김철수"]}}],"version":"2.30.5"}', '192.168.0.7', 'announcement', '건강 세미나 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 250 MINUTE), null, 3, NULL, '{"time":1724201390953,"blocks":[{"id":"1","type":"header","data":{"text":"사내 헬스장 오픈","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 헬스장이 9월 15일에 오픈합니다. 많은 이용 바랍니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 15일","장소: 본사 4층","운영시간: 월~금, 오전 8시~오후 8시"]}}],"version":"2.30.5"}', '192.168.0.8', 'announcement', '사내 헬스장 오픈 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 200 MINUTE), null, 3, NULL, '{"time":1724201390954,"blocks":[{"id":"1","type":"header","data":{"text":"사내 서류 정리 일정","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 서류 정리 작업이 8월 28일에 진행됩니다. 서류 정리에 협조 부탁드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 28일","장소: 각 부서 회의실","영향: 서류 접근 일시 제한"]}}],"version":"2.30.5"}', '192.168.0.9', 'announcement', '사내 서류 정리 일정', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 150 MINUTE), null, 3, NULL, '{"time":1724201390955,"blocks":[{"id":"1","type":"header","data":{"text":"팀 빌딩 활동 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 주 월요일 팀 빌딩 활동이 진행됩니다. 참여를 통해 팀워크를 다져보세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 26일 오후 3시","장소: 사내 운동장","활동: 팀 대항 게임"]}}],"version":"2.30.5"}', '192.168.0.10', 'announcement', '팀 빌딩 활동 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 100 MINUTE), null, 2, NULL, '{"time":1724201390956,"blocks":[{"id":"1","type":"header","data":{"text":"고객 피드백 세션 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"고객 피드백 세션이 8월 29일에 진행됩니다. 고객의 소리를 들을 수 있는 기회가 될 것입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 29일 오후 2시","장소: 본사 1층 대회의실","참석대상: 전직원"]}}],"version":"2.30.5"}', '192.168.0.11', 'announcement', '고객 피드백 세션 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 50 MINUTE), null, 2, NULL, '{"time":1724201390957,"blocks":[{"id":"1","type":"header","data":{"text":"사내 책 클럽 모임","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 책 클럽 모임이 8월 31일에 열립니다. 관심 있는 분들은 참석해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 31일 오후 4시","장소: 본사 5층 독서실","토론 주제: 최근 읽은 책"]}}],"version":"2.30.5"}', '192.168.0.12', 'announcement', '사내 책 클럽 모임 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 30 MINUTE), null, 2, NULL, '{"time":1724201390958,"blocks":[{"id":"1","type":"header","data":{"text":"사내 보안 교육 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 보안 교육이 9월 5일에 진행됩니다. 모든 직원은 필히 참석해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 5일 오전 10시","장소: 본사 6층 세미나실","강사: 보안 전문가 이민호"]}}],"version":"2.30.5"}', '192.168.0.13', 'announcement', '사내 보안 교육 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 10 MINUTE), null, 2, NULL, '{"time":1724201390959,"blocks":[{"id":"1","type":"header","data":{"text":"연구개발팀 회의 일정","level":3}},{"id":"2","type":"paragraph","data":{"text":"연구개발팀의 정기 회의가 8월 22일에 있습니다. 참여해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 22일 오전 9시","장소: 연구개발팀 회의실","안건: 프로젝트 진행 상황"]}}],"version":"2.30.5"}', '192.168.0.14', 'announcement', '연구개발팀 회의 일정', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 5 MINUTE), null, 2, NULL, '{"time":1724201390960,"blocks":[{"id":"1","type":"header","data":{"text":"사내 채용 공고","level":3}},{"id":"2","type":"paragraph","data":{"text":"신규 채용 공고가 게시되었습니다. 관심 있는 분들은 확인해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["직무: 마케팅 담당","자격요건: 3년 이상의 경력","접수 마감일: 2024년 9월 10일"]}}],"version":"2.30.5"}', '192.168.0.15', 'announcement', '사내 채용 공고', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 50 MINUTE), null, 1, NULL, '{"time":1724201390961,"blocks":[{"id":"1","type":"header","data":{"text":"IT 장비 업데이트 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"IT 장비 업데이트가 8월 27일에 진행됩니다. 업무에 참고해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 27일 오전 9시","장소: IT 부서","영향: 일부 시스템 다운"]}}],"version":"2.30.5"}', '192.168.0.16', 'announcement', 'IT 장비 업데이트 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 40 MINUTE), null, 1, NULL, '{"time":1724201390962,"blocks":[{"id":"1","type":"header","data":{"text":"부서별 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서별 성과 리뷰가 8월 30일에 진행됩니다. 모든 부서는 준비해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 30일 오후 2시","장소: 본사 3층 대회의실","참석자: 각 부서장"]}}],"version":"2.30.5"}', '192.168.0.17', 'announcement', '부서별 성과 리뷰', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 30 MINUTE), null, 1, NULL, '{"time":1724201390963,"blocks":[{"id":"1","type":"header","data":{"text":"사내 휴가 정책 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 휴가 정책이 변경되었습니다. 새로운 정책을 확인해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 변경일: 2024년 9월 1일","주요 변경 사항: 휴가 신청 절차 간소화","문의: 인사팀"]}}],"version":"2.30.5"}', '192.168.0.18', 'announcement', '사내 휴가 정책 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 20 MINUTE), null, 1, NULL, '{"time":1724201390964,"blocks":[{"id":"1","type":"header","data":{"text":"전사 회식 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"전사 회식이 9월 7일에 진행됩니다. 많은 참여 부탁드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 7일 오후 6시","장소: 사내 식당","특이사항: 식사 전 회의"]}}],"version":"2.30.5"}', '192.168.0.19', 'announcement', '전사 회식 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 10 MINUTE), null, 1, NULL, '{"time":1724201390965,"blocks":[{"id":"1","type":"header","data":{"text":"사내 환경 미화 활동","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 환경 미화 활동이 8월 29일에 진행됩니다. 모든 직원이 참여해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 29일 오전 10시","장소: 본사 전역","준비물: 장갑, 청소 도구"]}}],"version":"2.30.5"}', '192.168.0.20', 'announcement', '사내 환경 미화 활동', 'NOTICE');

INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 1, '2024-08-21 10:29:57.484617', NULL, '매출 목표와 고객 만족도 목표 모두 매우 도전적입니다! 👍 특히 매출 목표를 달성하기 위해 마케팅을 강화하는 것은 좋은 접근입니다. 이를 위해 구체적인 마케팅 전략과 캠페인 계획이 필요할 것 같습니다. 추가적으로, 고객 만족도 90% 달성을 위한 서비스 개선 방안도 세부적으로 검토해볼 필요가 있을 것 같습니다. 이를 위해 고객 피드백을 적극적으로 반영하는 것이 중요할 것 같아요.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 2, '2024-08-21 10:30:06.083897', NULL, '팀 목표 설정을 잘하셨습니다. 매출 목표와 고객 만족도 목표는 명확하고 실현 가능한 목표로 보입니다. 고객 만족도를 높이기 위한 서비스 개선은 고객의 목소리를 직접 듣고 반영하는 것이 핵심이겠죠? 이를 위해 정기적인 고객 피드백 세션을 계획하고, 고객의 불만 사항을 신속히 해결할 수 있는 시스템을 구축하면 좋을 것 같습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 3, '2024-08-21 10:30:16.144943', NULL, '매출 목표 100% 달성과 고객 만족도 90%는 모두 중요한 목표입니다. 매출 목표를 달성하기 위한 마케팅 강화 계획은 좋지만, 예상되는 도전 과제나 문제점을 미리 파악하고 대응 전략을 세우는 것도 도움이 될 것입니다. 또한, 서비스 개선을 위한 구체적인 액션 플랜을 수립하여 팀원들이 각자의 역할을 명확히 이해하고 실행할 수 있도록 하는 것이 중요합니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 4, '2024-08-21 10:30:18.311632', NULL, '목표 설정이 매우 명확합니다. 목표 달성을 위해 계획된 마케팅 강화와 서비스 개선이 성공적으로 진행되기를 바랍니다. 이를 위해 각 팀원들이 맡은 바 역할을 충실히 수행할 수 있도록 지원할 방안도 고려해야 할 것 같습니다. 예를 들어, 마케팅 팀과 고객 서비스 팀 간의 협업을 강화하는 방안을 고민해보는 것도 좋을 것 같습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 5, '2024-08-21 10:30:31.591583', NULL, '팀 목표 설정에 대한 계획이 잘 되어 있습니다. 목표 달성을 위해 각 목표에 대해 구체적인 KPI를 설정하고, 이를 지속적으로 모니터링하는 것이 중요할 것 같습니다. 목표 달성 상황을 주기적으로 점검하고, 필요 시 계획을 수정하여 유연하게 대응하는 것도 효과적일 것입니다. 모든 팀원이 목표를 공유하고 함께 노력하면 좋은 결과를 얻을 수 있을 것입니다.');
INSERT INTO comment (board_id, author_id, created_date,  parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:30:48.097925', 1, '좋은 피드백 감사합니다! 매출 목표 달성을 위한 마케팅 전략은 현재 두 가지 주요 캠페인을 계획 중에 있습니다. 하나는 소셜 미디어 광고 강화이고, 다른 하나는 이메일 마케팅 캠페인입니다. 고객 만족도 90% 달성을 위해서는 고객 피드백을 직접 반영할 수 있는 시스템을 구축하고, 이를 통해 얻은 데이터를 분석하여 구체적인 개선안을 도출할 예정입니다. 추가적인 제안이 있으면 언제든지 말씀해 주세요!');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:30:59.776921', 2, '소중한 의견 감사합니다. 맞습니다, 고객 피드백을 적극적으로 반영하는 것이 중요하죠. 고객의 의견을 수집하는 방법으로는 설문조사와 직접 인터뷰를 고려하고 있습니다. 이를 통해 서비스 개선 방향을 명확히 하고, 실질적인 문제 해결 방안을 마련할 수 있을 것입니다. 피드백 세션의 구체적인 일정이나 방법에 대해 더 논의해보면 좋겠습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:31:08.383114', 3, '말씀하신 대로, 도전 과제를 사전에 파악하고 대응 전략을 마련하는 것이 중요하다는 점에 동의합니다. 특히 마케팅 강화 측면에서, 경쟁사 분석과 고객 행동 분석을 통해 예상되는 문제점을 미리 식별하고 대비할 계획입니다. 서비스 개선의 경우, 고객의 불만 사항을 빠르게 해결할 수 있는 시스템을 구축하여 문제가 발생했을 때 신속히 대응할 수 있도록 하겠습니다. 좋은 제안 감사합니다!');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUE
    (58, 8, '2024-08-21 10:31:14.823746', 4, '팀 간 협업 강화에 대한 의견도 감사합니다. 마케팅 팀과 고객 서비스 팀 간의 협력을 통해 더 나은 결과를 얻을 수 있을 것이라 생각합니다. 이를 위해 정기적인 협업 미팅과 공동 프로젝트를 통해 두 팀 간의 소통을 강화할 계획입니다. 각 팀의 역할과 책임을 명확히 하고, 협업의 효율성을 높이기 위한 방안을 마련해보겠습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:31:20.584126', 5, '정확한 지적 감사합니다. KPI 설정과 지속적인 모니터링은 목표 달성의 핵심이죠. KPI를 설정할 때, 구체적이고 측정 가능한 지표를 설정하여 목표 달성 상황을 명확히 파악할 수 있도록 하겠습니다. 주기적인 점검과 필요시 계획 수정에 대한 의견도 적극 반영하여, 팀원들이 목표에 집중할 수 있도록 지원하겠습니다. 의견 주셔서 감사합니다!');


INSERT INTO chat_room(name) values ('전체공지');
INSERT INTO chat_room_participants (chat_room_id, participant_id)
SELECT 1, employee_id
FROM employee;

INSERT INTO chat_room(name) values ('부서채팅');
INSERT INTO chat_room_participants (chat_room_id, participant_id)
SELECT 2, employee_id
FROM employee WHERE department_id = 1;

INSERT INTO attendance (overtime_hours, work_hours, clock_in_time, clock_out_time, date, employee_id, status)
VALUES
    (0, 540, '2024-07-01 09:00:00.388684', '2024-07-01 18:00:00.388684', '2024-07-01 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-02 09:00:00.388684', '2024-07-02 18:00:00.388684', '2024-07-02 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-03 09:00:00.388684', '2024-07-03 18:00:00.388684', '2024-07-03 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-04 09:00:00.388684', '2024-07-04 18:00:00.388684', '2024-07-04 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-05 09:00:00.388684', '2024-07-05 18:00:00.388684', '2024-07-05 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-08 09:00:00.388684', '2024-07-08 18:00:00.388684', '2024-07-08 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-09 09:00:00.388684', '2024-07-09 18:00:00.388684', '2024-07-09 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-10 09:00:00.388684', '2024-07-10 18:00:00.388684', '2024-07-10 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-11 09:00:00.388684', '2024-07-11 18:00:00.388684', '2024-07-11 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-12 09:00:00.388684', '2024-07-12 18:00:00.388684', '2024-07-12 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-15 09:00:00.388684', '2024-07-15 18:00:00.388684', '2024-07-15 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-16 09:00:00.388684', '2024-07-16 18:00:00.388684', '2024-07-16 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-17 09:00:00.388684', '2024-07-17 18:00:00.388684', '2024-07-17 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-18 09:00:00.388684', '2024-07-18 18:00:00.388684', '2024-07-18 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-19 09:00:00.388684', '2024-07-19 18:00:00.388684', '2024-07-19 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-22 09:00:00.388684', '2024-07-22 18:00:00.388684', '2024-07-22 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-23 09:00:00.388684', '2024-07-23 18:00:00.388684', '2024-07-23 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-24 09:00:00.388684', '2024-07-24 18:00:00.388684', '2024-07-24 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-25 09:00:00.388684', '2024-07-25 18:00:00.388684', '2024-07-25 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-26 09:00:00.388684', '2024-07-26 18:00:00.388684', '2024-07-26 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-29 09:00:00.388684', '2024-07-29 18:00:00.388684', '2024-07-29 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-30 09:00:00.388684', '2024-07-30 18:00:00.388684', '2024-07-30 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-31 09:00:00.388684', '2024-07-31 18:00:00.388684', '2024-07-31 18:00:00.388684', 1, '🌟 출퇴근 완료');

INSERT INTO attendance (overtime_hours, work_hours, clock_in_time, clock_out_time, date, employee_id, status)
VALUES
    (0, 540, '2024-08-01 09:00:00.388684', '2024-08-01 18:00:00.388684', '2024-08-01 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-02 09:00:00.388684', '2024-08-02 18:00:00.388684', '2024-08-02 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-05 09:00:00.388684', '2024-08-05 18:00:00.388684', '2024-08-05 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-06 09:00:00.388684', '2024-08-06 18:00:00.388684', '2024-08-06 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (120, 540, '2024-08-07 09:00:00.388684', '2024-08-07 20:00:00.388684', '2024-08-07 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-08 09:00:00.388684', '2024-08-08 18:00:00.388684', '2024-08-08 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-09 09:00:00.388684', '2024-08-09 18:00:00.388684', '2024-08-09 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-12 09:00:00.388684', '2024-08-12 18:00:00.388684', '2024-08-12 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (180, 540, '2024-08-14 09:00:00.388684', '2024-08-14 21:00:00.388684', '2024-08-14 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-15 09:00:00.388684', '2024-08-15 18:00:00.388684', '2024-08-15 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-16 09:00:00.388684', '2024-08-16 18:00:00.388684', '2024-08-16 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-17 09:00:00.388684', '2024-08-17 18:00:00.388684', '2024-08-17 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-20 09:00:00.388684', '2024-08-20 18:00:00.388684', '2024-08-20 18:00:00.388684', 1, '🌟 출퇴근 완료');

/* ====== workflow start ======== */

insert into groupworks.workflow (approval_count, approver_count, status, work_flow_type, approval_date, cost, department_id, draft_date, employee_id, final_employee_id, workflow_id, description, code, department, email, employee_name, employee_rank, final_approval_department, final_approval_name, final_approval_rank, phone, title)
values  (3, 3, 1, 3, '2024-07-14 01:35:35.000000', 1200000, 1, '2024-07-11 01:35:35.000000', 10, 1, 1, '기술부서 95AAD5 예산 기안 요청', 'a6879373-2214-4fac-9b86-0a25567bd9c0', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 95AAD5 예산 기안 요청'),
        (0, 3, 0, 4, null, 0, 1, '2024-02-06 07:44:53.000000', 10, 1, 2, '기술부서 9257B7 비품 구매사항 비용 청구입니다.', '37a7eedf-ca3d-44bd-89bc-fe283317329d', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 9257B7 비품 구매사항 비용 청구입니다.'),
        (0, 3, 0, 1, null, 0, 1, '2024-07-27 05:56:21.000000', 10, 1, 3, '기술부서 192D73 업무 기안 결재', 'c54afd51-30cc-4b22-86e9-678be96a64b2', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 192D73 업무 기안 결재'),
        (3, 3, 1, 6, '2024-01-11 23:30:42.000000', 0, 1, '2024-01-08 23:30:42.000000', 10, 1, 4, '기술부서 B72E2C 문제 발생 관련하여 결재 요청바랍니다.', 'b70932eb-20fe-4604-bf02-80d510175e45', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 B72E2C 문제 발생 관련하여 결재 요청바랍니다.'),
        (3, 3, 1, 2, '2024-01-15 13:52:40.000000', 2400000, 1, '2024-01-12 13:52:40.000000', 10, 1, 5, '기술부서 D09173 재무 결산입니다.', '4691855f-f149-4032-b1e3-eb35b6e559a8', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 D09173 재무 결산입니다.'),
        (3, 3, 1, 4, '2024-01-16 05:03:11.000000', 1200000, 1, '2024-01-13 05:03:11.000000', 10, 1, 6, '기술부서 33E98F 비품 구매사항 비용 청구입니다.', 'afa39dc8-8846-4734-97bf-a0efa8e20755', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 33E98F 비품 구매사항 비용 청구입니다.'),
        (1, 3, 2, 3, '2024-02-29 03:34:03.000000', 0, 1, '2024-02-26 03:34:03.000000', 10, 1, 7, '기술부서 A39828 예산 기안 요청', '95ccc505-cc5e-455f-bd40-9eb556d9359b', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 A39828 예산 기안 요청'),
        (1, 3, 2, 3, '2024-02-01 14:16:47.000000', 1200000, 1, '2024-01-29 14:16:47.000000', 10, 1, 8, '기술부서 D746CB 예산 기안 요청', 'bb85a0bc-83f1-4896-b4b6-dba8a3222ce8', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 D746CB 예산 기안 요청'),
        (3, 3, 1, 5, '2024-07-16 02:29:36.000000', 0, 1, '2024-07-13 02:29:36.000000', 10, 1, 9, '기술부서 12354C 보고서 관련 결재 요청입니다.', 'acbdbd2b-85fb-4e2b-b899-48d62fc40dd1', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 12354C 보고서 관련 결재 요청입니다.'),
        (1, 3, 2, 1, '2024-05-23 12:13:41.000000', 0, 1, '2024-05-20 12:13:41.000000', 10, 1, 10, '기술부서 D8961C 업무 기안 결재', 'd87e0c79-5029-4967-bf61-642be9809071', '기술부서', 'pcy@gw.com', '박채윤', '사원', '기술부서', '이정민', '부장', '010-1234-5609', '기술부서 D8961C 업무 기안 결재'),
        (3, 3, 1, 6, '2024-04-27 11:27:32.000000', 0, 2, '2024-04-24 11:27:32.000000', 11, 11, 11, '마케팅부서 F8DAD2 문제 발생 관련하여 결재 요청바랍니다.', '5f1a309b-977d-4460-a6a6-4419cbcfea71', '마케팅부서', 'employee11@gw.com', '사원11', '부장', '마케팅부서', '사원11', '부장', '010-1234-5610', '마케팅부서 F8DAD2 문제 발생 관련하여 결재 요청바랍니다.'),
        (1, 3, 1, 4, '2024-08-13 21:29:37.000000', 0, 2, '2024-08-10 21:29:37.000000', 12, 12, 12, '마케팅부서 746930 비품 구매사항 비용 청구입니다.', 'fc000e35-9150-479e-b21f-24a0ef72cd98', '마케팅부서', 'employee12@gw.com', '사원12', '차장', '마케팅부서', '사원12', '차장', '010-1234-5611', '마케팅부서 746930 비품 구매사항 비용 청구입니다.'),
        (0, 3, 0, 4, '2024-01-22 01:38:09.000000', 0, 2, '2024-01-19 01:38:09.000000', 13, 13, 13, '마케팅부서 9FD79D 비품 구매사항 비용 청구입니다.', '19682a7d-4be0-4171-8728-5c53395fc7e2', '마케팅부서', 'employee13@gw.com', '사원13', '과장', '마케팅부서', '사원13', '과장', '010-1234-5612', '마케팅부서 9FD79D 비품 구매사항 비용 청구입니다.'),
        (1, 3, 2, 2, '2024-05-10 04:26:16.000000', 2400000, 2, '2024-05-07 04:26:16.000000', 14, 14, 14, '마케팅부서 819047 재무 결산입니다.', '81f6a4bf-4d98-4e88-b79f-5b4896aeffae', '마케팅부서', 'employee14@gw.com', '사원14', '과장', '마케팅부서', '사원14', '과장', '010-1234-5613', '마케팅부서 819047 재무 결산입니다.'),
        (2, 3, 1, 3, '2024-07-22 19:48:34.000000', 0, 2, '2024-07-19 19:48:34.000000', 15, 15, 15, '마케팅부서 3CD8DB 예산 기안 요청', '8de89afa-a851-459a-8f2b-00461c165edb', '마케팅부서', 'employee15@gw.com', '사원15', '대리', '마케팅부서', '사원15', '대리', '010-1234-5614', '마케팅부서 3CD8DB 예산 기안 요청'),
        (0, 3, 2, 2, '2024-06-18 12:21:43.000000', 0, 2, '2024-06-15 12:21:43.000000', 16, 16, 16, '마케팅부서 A6AA2F 재무 결산입니다.', '10c67d06-af87-4a3b-9711-86efe2f20889', '마케팅부서', 'employee16@gw.com', '사원16', '대리', '마케팅부서', '사원16', '대리', '010-1234-5615', '마케팅부서 A6AA2F 재무 결산입니다.'),
        (2, 3, 0, 6, '2024-01-08 23:51:04.000000', 0, 2, '2024-01-05 23:51:04.000000', 17, 17, 17, '마케팅부서 718FB9 문제 발생 관련하여 결재 요청바랍니다.', 'adb6fca5-55f5-4608-997c-d7b6448b8757', '마케팅부서', 'employee17@gw.com', '사원17', '사원', '마케팅부서', '사원17', '사원', '010-1234-5616', '마케팅부서 718FB9 문제 발생 관련하여 결재 요청바랍니다.'),
        (2, 3, 2, 4, '2024-01-25 12:47:53.000000', 0, 2, '2024-01-22 12:47:53.000000', 18, 18, 18, '마케팅부서 217155 비품 구매사항 비용 청구입니다.', '34aae03d-7511-4d01-a2c1-4eef444e04b6', '마케팅부서', 'employee18@gw.com', '사원18', '사원', '마케팅부서', '사원18', '사원', '010-1234-5617', '마케팅부서 217155 비품 구매사항 비용 청구입니다.'),
        (2, 3, 2, 5, '2024-05-03 23:51:35.000000', 1200000, 2, '2024-04-30 23:51:35.000000', 19, 19, 19, '마케팅부서 5130B0 보고서 관련 결재 요청입니다.', '20367610-7f2f-44a4-bc21-16c13448a247', '마케팅부서', 'employee19@gw.com', '사원19', '사원', '마케팅부서', '사원19', '사원', '010-1234-5618', '마케팅부서 5130B0 보고서 관련 결재 요청입니다.'),
        (3, 3, 1, 1, '2024-02-20 15:11:39.000000', 2400000, 2, '2024-02-17 15:11:39.000000', 20, 20, 20, '마케팅부서 C0929D 업무 기안 결재', 'c4cf939d-6a7e-4231-971e-d38cdf8aa01a', '마케팅부서', 'employee20@gw.com', '사원20', '사원', '마케팅부서', '사원20', '사원', '010-1234-5619', '마케팅부서 C0929D 업무 기안 결재'),
        (2, 3, 0, 2, '2024-05-29 18:15:13.000000', 1200000, 3, '2024-05-26 18:15:13.000000', 21, 21, 21, '영업부서 7AD24D 재무 결산입니다.', '69d46c11-2da5-4cc2-8e8f-8df0d5f4f45e', '영업부서', 'employee21@gw.com', '사원21', '부장', '영업부서', '사원21', '부장', '010-1234-5620', '영업부서 7AD24D 재무 결산입니다.'),
        (3, 3, 1, 2, '2024-01-24 08:53:59.000000', 0, 3, '2024-01-21 08:53:59.000000', 22, 22, 22, '영업부서 315158 재무 결산입니다.', '2db38622-79f8-4aeb-8fef-129a8fb3a7a0', '영업부서', 'employee22@gw.com', '사원22', '차장', '영업부서', '사원22', '차장', '010-1234-5621', '영업부서 315158 재무 결산입니다.'),
        (2, 3, 0, 4, '2024-07-27 04:18:18.000000', 0, 3, '2024-07-24 04:18:18.000000', 23, 23, 23, '영업부서 28D981 비품 구매사항 비용 청구입니다.', '324b4b03-60ef-4354-8ad2-063adb440e43', '영업부서', 'employee23@gw.com', '사원23', '과장', '영업부서', '사원23', '과장', '010-1234-5622', '영업부서 28D981 비품 구매사항 비용 청구입니다.'),
        (2, 3, 0, 6, '2024-08-08 03:07:07.000000', 2400000, 3, '2024-08-05 03:07:07.000000', 24, 24, 24, '영업부서 FCF2AC 문제 발생 관련하여 결재 요청바랍니다.', '49381177-c45c-4daf-b41b-baaf2ece2b02', '영업부서', 'employee24@gw.com', '사원24', '과장', '영업부서', '사원24', '과장', '010-1234-5623', '영업부서 FCF2AC 문제 발생 관련하여 결재 요청바랍니다.'),
        (0, 3, 2, 1, '2024-01-06 00:40:05.000000', 2400000, 3, '2024-01-03 00:40:05.000000', 25, 25, 25, '영업부서 77CE4D 업무 기안 결재', '68967583-b081-4c18-897b-61ddc3396292', '영업부서', 'employee25@gw.com', '사원25', '대리', '영업부서', '사원25', '대리', '010-1234-5624', '영업부서 77CE4D 업무 기안 결재'),
        (0, 3, 0, 5, '2024-07-01 17:12:11.000000', 1200000, 3, '2024-06-28 17:12:11.000000', 26, 26, 26, '영업부서 7991F1 보고서 관련 결재 요청입니다.', 'e080c5a6-54b4-4b22-bfe2-7398bb1af273', '영업부서', 'employee26@gw.com', '사원26', '대리', '영업부서', '사원26', '대리', '010-1234-5625', '영업부서 7991F1 보고서 관련 결재 요청입니다.'),
        (1, 3, 0, 3, '2024-03-15 19:17:49.000000', 2400000, 3, '2024-03-12 19:17:49.000000', 27, 27, 27, '영업부서 6825CF 예산 기안 요청', 'd3f7ab08-ab17-4989-a241-a20478f80f06', '영업부서', 'employee27@gw.com', '사원27', '사원', '영업부서', '사원27', '사원', '010-1234-5626', '영업부서 6825CF 예산 기안 요청'),
        (2, 3, 0, 1, '2024-02-25 11:32:40.000000', 0, 3, '2024-02-22 11:32:40.000000', 28, 28, 28, '영업부서 1A4CAA 업무 기안 결재', '2fe1dc83-b417-45f8-b386-830c66999bc9', '영업부서', 'employee28@gw.com', '사원28', '사원', '영업부서', '사원28', '사원', '010-1234-5627', '영업부서 1A4CAA 업무 기안 결재'),
        (3, 3, 0, 2, '2024-04-06 00:04:03.000000', 0, 3, '2024-04-03 00:04:03.000000', 29, 29, 29, '영업부서 76B4C6 재무 결산입니다.', '72101cfa-d64f-4508-b510-eac3017284f0', '영업부서', 'employee29@gw.com', '사원29', '사원', '영업부서', '사원29', '사원', '010-1234-5628', '영업부서 76B4C6 재무 결산입니다.'),
        (0, 3, 2, 1, '2024-07-03 11:59:01.000000', 0, 3, '2024-06-30 11:59:01.000000', 30, 30, 30, '영업부서 598B2F 업무 기안 결재', '0b036562-640e-4f30-94e1-8cb2c31dc557', '영업부서', 'employee30@gw.com', '사원30', '사원', '영업부서', '사원30', '사원', '010-1234-5629', '영업부서 598B2F 업무 기안 결재'),
        (2, 3, 0, 4, '2024-04-26 11:52:45.000000', 0, 4, '2024-04-23 11:52:45.000000', 31, 31, 31, '인사부서 967A98 비품 구매사항 비용 청구입니다.', 'c350062e-cc69-44b0-92ee-4b0ca555943f', '인사부서', 'employee31@gw.com', '사원31', '부장', '인사부서', '사원31', '부장', '010-1234-5630', '인사부서 967A98 비품 구매사항 비용 청구입니다.'),
        (3, 3, 0, 1, '2024-04-03 06:49:47.000000', 0, 4, '2024-03-31 06:49:47.000000', 32, 32, 32, '인사부서 343818 업무 기안 결재', '347dc0bf-8278-4421-a0ba-a3a5d59f6f9c', '인사부서', 'employee32@gw.com', '사원32', '차장', '인사부서', '사원32', '차장', '010-1234-5631', '인사부서 343818 업무 기안 결재'),
        (2, 3, 0, 3, '2024-03-24 01:54:42.000000', 0, 4, '2024-03-21 01:54:42.000000', 33, 33, 33, '인사부서 ADB8B2 예산 기안 요청', '8874f189-fb74-4340-a6f7-562ee76dba3b', '인사부서', 'employee33@gw.com', '사원33', '과장', '인사부서', '사원33', '과장', '010-1234-5632', '인사부서 ADB8B2 예산 기안 요청'),
        (0, 3, 2, 2, '2024-03-19 23:35:05.000000', 2400000, 4, '2024-03-16 23:35:05.000000', 34, 34, 34, '인사부서 B81EF2 재무 결산입니다.', '8494b1c1-f6f6-4ad9-83ff-e5b047313c48', '인사부서', 'employee34@gw.com', '사원34', '과장', '인사부서', '사원34', '과장', '010-1234-5633', '인사부서 B81EF2 재무 결산입니다.'),
        (1, 3, 2, 1, '2024-01-09 19:48:20.000000', 0, 4, '2024-01-06 19:48:20.000000', 35, 35, 35, '인사부서 4B7CAA 업무 기안 결재', '18b8d935-757a-4103-aa35-0a9a145981e7', '인사부서', 'employee35@gw.com', '사원35', '대리', '인사부서', '사원35', '대리', '010-1234-5634', '인사부서 4B7CAA 업무 기안 결재'),
        (1, 3, 2, 5, '2024-07-23 19:07:39.000000', 0, 4, '2024-07-20 19:07:39.000000', 36, 36, 36, '인사부서 02A126 보고서 관련 결재 요청입니다.', '3836f864-9572-4249-8589-e9374ccb25a4', '인사부서', 'employee36@gw.com', '사원36', '대리', '인사부서', '사원36', '대리', '010-1234-5635', '인사부서 02A126 보고서 관련 결재 요청입니다.'),
        (1, 3, 1, 4, '2024-03-17 15:38:51.000000', 1200000, 4, '2024-03-14 15:38:51.000000', 37, 37, 37, '인사부서 C3B8B2 비품 구매사항 비용 청구입니다.', '6b199ae4-4852-4a8d-9214-116a789cdd17', '인사부서', 'employee37@gw.com', '사원37', '사원', '인사부서', '사원37', '사원', '010-1234-5636', '인사부서 C3B8B2 비품 구매사항 비용 청구입니다.'),
        (1, 3, 2, 5, '2024-07-27 00:50:35.000000', 0, 4, '2024-07-24 00:50:35.000000', 38, 38, 38, '인사부서 24AA41 보고서 관련 결재 요청입니다.', '175fd83c-6b81-4123-b5a8-089c1eb2ce05', '인사부서', 'employee38@gw.com', '사원38', '사원', '인사부서', '사원38', '사원', '010-1234-5637', '인사부서 24AA41 보고서 관련 결재 요청입니다.'),
        (2, 3, 2, 1, '2024-08-05 09:55:29.000000', 0, 4, '2024-08-02 09:55:29.000000', 39, 39, 39, '인사부서 A1C9DA 업무 기안 결재', '72ca0506-68f5-4b17-9389-4f5b9750d77d', '인사부서', 'employee39@gw.com', '사원39', '사원', '인사부서', '사원39', '사원', '010-1234-5638', '인사부서 A1C9DA 업무 기안 결재'),
        (0, 3, 1, 4, '2024-01-22 17:23:09.000000', 0, 4, '2024-01-19 17:23:09.000000', 40, 40, 40, '인사부서 46281E 비품 구매사항 비용 청구입니다.', '142135dc-3b15-4967-81df-94b5287f8f4f', '인사부서', 'employee40@gw.com', '사원40', '사원', '인사부서', '사원40', '사원', '010-1234-5639', '인사부서 46281E 비품 구매사항 비용 청구입니다.'),
        (0, 3, 1, 4, '2024-08-14 04:15:59.000000', 1200000, 5, '2024-08-11 04:15:59.000000', 41, 41, 41, '재무부서 78A1EA 비품 구매사항 비용 청구입니다.', 'bab43fad-b45b-4840-bc21-e0282489a1e6', '재무부서', 'employee41@gw.com', '사원41', '부장', '재무부서', '사원41', '부장', '010-1234-5640', '재무부서 78A1EA 비품 구매사항 비용 청구입니다.'),
        (3, 3, 1, 3, '2024-08-15 12:56:19.000000', 1200000, 5, '2024-08-12 12:56:19.000000', 42, 42, 42, '재무부서 454B22 예산 기안 요청', '5bbd07fe-cbe9-4c52-92be-a17e70f61d70', '재무부서', 'employee42@gw.com', '사원42', '차장', '재무부서', '사원42', '차장', '010-1234-5641', '재무부서 454B22 예산 기안 요청'),
        (0, 3, 0, 6, '2024-03-27 00:42:36.000000', 1200000, 5, '2024-03-24 00:42:36.000000', 43, 43, 43, '재무부서 CCBED7 문제 발생 관련하여 결재 요청바랍니다.', '7fea250d-32c0-4251-af88-2c036a6804a2', '재무부서', 'employee43@gw.com', '사원43', '과장', '재무부서', '사원43', '과장', '010-1234-5642', '재무부서 CCBED7 문제 발생 관련하여 결재 요청바랍니다.'),
        (3, 3, 1, 5, '2024-07-04 23:13:12.000000', 0, 5, '2024-07-01 23:13:12.000000', 44, 44, 44, '재무부서 EAA6A8 보고서 관련 결재 요청입니다.', 'adc250a3-226f-4191-9a10-9eb00f35fecb', '재무부서', 'employee44@gw.com', '사원44', '과장', '재무부서', '사원44', '과장', '010-1234-5643', '재무부서 EAA6A8 보고서 관련 결재 요청입니다.'),
        (0, 3, 1, 1, '2024-03-24 20:38:52.000000', 2400000, 5, '2024-03-21 20:38:52.000000', 45, 45, 45, '재무부서 05A4E6 업무 기안 결재', 'f1eabb0e-eaa0-4cda-8661-8d37ccd4e295', '재무부서', 'employee45@gw.com', '사원45', '대리', '재무부서', '사원45', '대리', '010-1234-5644', '재무부서 05A4E6 업무 기안 결재'),
        (0, 3, 2, 3, '2024-01-15 23:59:38.000000', 0, 5, '2024-01-12 23:59:38.000000', 46, 46, 46, '재무부서 8F6A37 예산 기안 요청', '6d322468-e795-442e-a58c-e54f12c6ec01', '재무부서', 'employee46@gw.com', '사원46', '대리', '재무부서', '사원46', '대리', '010-1234-5645', '재무부서 8F6A37 예산 기안 요청'),
        (2, 3, 1, 6, '2024-03-16 21:34:19.000000', 2400000, 5, '2024-03-13 21:34:19.000000', 47, 47, 47, '재무부서 B9CF0E 문제 발생 관련하여 결재 요청바랍니다.', 'c7d2fe66-ddd3-42f4-ba4d-bcbabb4d5b13', '재무부서', 'employee47@gw.com', '사원47', '사원', '재무부서', '사원47', '사원', '010-1234-5646', '재무부서 B9CF0E 문제 발생 관련하여 결재 요청바랍니다.'),
        (1, 3, 2, 4, '2024-02-08 18:12:35.000000', 1200000, 5, '2024-02-05 18:12:35.000000', 48, 48, 48, '재무부서 BBE2B7 비품 구매사항 비용 청구입니다.', 'd059a003-7c60-41c3-855f-53124e3010a3', '재무부서', 'employee48@gw.com', '사원48', '사원', '재무부서', '사원48', '사원', '010-1234-5647', '재무부서 BBE2B7 비품 구매사항 비용 청구입니다.'),
        (3, 3, 0, 2, '2024-06-27 11:24:32.000000', 0, 5, '2024-06-24 11:24:32.000000', 49, 49, 49, '재무부서 B5F619 재무 결산입니다.', '0c4b73b5-fc45-43fb-989e-0acd8c281011', '재무부서', 'employee49@gw.com', '사원49', '사원', '재무부서', '사원49', '사원', '010-1234-5648', '재무부서 B5F619 재무 결산입니다.'),
        (1, 3, 2, 6, '2024-02-27 03:47:34.000000', 0, 5, '2024-02-24 03:47:34.000000', 50, 50, 50, '재무부서 A5AD3D 문제 발생 관련하여 결재 요청바랍니다.', 'd5c03dbe-8696-49d1-b053-42feb4d6f55f', '재무부서', 'employee50@gw.com', '사원50', '사원', '재무부서', '사원50', '사원', '010-1234-5649', '재무부서 A5AD3D 문제 발생 관련하여 결재 요청바랍니다.'),
        (0, 3, 0, 3, '2024-04-18 14:13:37.000000', 1200000, 6, '2024-04-15 14:13:37.000000', 51, 51, 51, 'IT 지원부서 A48D77 예산 기안 요청', '1df26caa-ec2a-4a95-94c4-6de5066bdb2a', 'IT 지원부서', 'employee51@gw.com', '사원51', '부장', 'IT 지원부서', '사원51', '부장', '010-1234-5650', 'IT 지원부서 A48D77 예산 기안 요청'),
        (1, 3, 2, 4, '2024-04-22 14:41:58.000000', 2400000, 6, '2024-04-19 14:41:58.000000', 52, 52, 52, 'IT 지원부서 50FE11 비품 구매사항 비용 청구입니다.', 'cad4e7d7-ca6e-4b74-a751-b844cfb18c03', 'IT 지원부서', 'employee52@gw.com', '사원52', '차장', 'IT 지원부서', '사원52', '차장', '010-1234-5651', 'IT 지원부서 50FE11 비품 구매사항 비용 청구입니다.'),
        (0, 3, 2, 4, '2024-01-21 05:56:21.000000', 2400000, 6, '2024-01-18 05:56:21.000000', 53, 53, 53, 'IT 지원부서 BB2599 비품 구매사항 비용 청구입니다.', '3f2bd54f-3667-478c-976d-74a1d8865b8a', 'IT 지원부서', 'employee53@gw.com', '사원53', '과장', 'IT 지원부서', '사원53', '과장', '010-1234-5652', 'IT 지원부서 BB2599 비품 구매사항 비용 청구입니다.'),
        (1, 3, 0, 3, '2024-03-17 04:27:22.000000', 1200000, 6, '2024-03-14 04:27:22.000000', 54, 54, 54, 'IT 지원부서 BD721C 예산 기안 요청', '02fd2fef-bcef-44a6-b372-a2fec87f80b6', 'IT 지원부서', 'employee54@gw.com', '사원54', '과장', 'IT 지원부서', '사원54', '과장', '010-1234-5653', 'IT 지원부서 BD721C 예산 기안 요청'),
        (3, 3, 1, 5, '2024-06-05 17:19:16.000000', 0, 6, '2024-06-02 17:19:16.000000', 55, 55, 55, 'IT 지원부서 F97BCC 보고서 관련 결재 요청입니다.', '67058140-d750-4c87-9a43-5bb401f4db2b', 'IT 지원부서', 'employee55@gw.com', '사원55', '대리', 'IT 지원부서', '사원55', '대리', '010-1234-5654', 'IT 지원부서 F97BCC 보고서 관련 결재 요청입니다.'),
        (2, 3, 2, 4, '2024-02-25 13:13:51.000000', 1200000, 6, '2024-02-22 13:13:51.000000', 56, 56, 56, 'IT 지원부서 7336B6 비품 구매사항 비용 청구입니다.', 'c8d4b31a-5239-4057-888c-12a1d55573a0', 'IT 지원부서', 'employee56@gw.com', '사원56', '대리', 'IT 지원부서', '사원56', '대리', '010-1234-5655', 'IT 지원부서 7336B6 비품 구매사항 비용 청구입니다.'),
        (1, 3, 1, 2, '2024-08-03 20:33:54.000000', 2400000, 6, '2024-07-31 20:33:54.000000', 57, 57, 57, 'IT 지원부서 08B23E 재무 결산입니다.', '202b25d1-d17c-401e-9538-07377f0ff9af', 'IT 지원부서', 'employee57@gw.com', '사원57', '사원', 'IT 지원부서', '사원57', '사원', '010-1234-5656', 'IT 지원부서 08B23E 재무 결산입니다.'),
        (0, 3, 2, 1, '2024-01-18 00:16:59.000000', 0, 6, '2024-01-15 00:16:59.000000', 58, 58, 58, 'IT 지원부서 D49BCF 업무 기안 결재', 'b1f6cb6a-0eb6-4483-b7b5-7c94df0364b1', 'IT 지원부서', 'employee58@gw.com', '사원58', '사원', 'IT 지원부서', '사원58', '사원', '010-1234-5657', 'IT 지원부서 D49BCF 업무 기안 결재'),
        (1, 3, 0, 4, '2024-04-07 10:22:18.000000', 2400000, 6, '2024-04-04 10:22:18.000000', 59, 59, 59, 'IT 지원부서 AF9D07 비품 구매사항 비용 청구입니다.', '287d006e-82f4-4ba1-b477-e8adbb26572b', 'IT 지원부서', 'employee59@gw.com', '사원59', '사원', 'IT 지원부서', '사원59', '사원', '010-1234-5658', 'IT 지원부서 AF9D07 비품 구매사항 비용 청구입니다.'),
        (1, 3, 2, 1, '2024-04-28 13:52:20.000000', 0, 6, '2024-04-25 13:52:20.000000', 60, 60, 60, 'IT 지원부서 63E0E5 업무 기안 결재', '37650180-d83c-4f8d-aa0d-88ddc31511a3', 'IT 지원부서', 'employee60@gw.com', '사원60', '사원', 'IT 지원부서', '사원60', '사원', '010-1234-5659', 'IT 지원부서 63E0E5 업무 기안 결재'),
        (3, 3, 1, 5, '2024-02-25 23:12:45.000000', 0, 7, '2024-02-22 23:12:45.000000', 61, 61, 61, '제품 관리부서 760DFB 보고서 관련 결재 요청입니다.', '6625c776-1191-409b-a307-649805dfc89f', '제품 관리부서', 'employee61@gw.com', '사원61', '부장', '제품 관리부서', '사원61', '부장', '010-1234-5660', '제품 관리부서 760DFB 보고서 관련 결재 요청입니다.'),
        (1, 3, 1, 4, '2024-02-29 15:45:45.000000', 1200000, 7, '2024-02-26 15:45:45.000000', 62, 62, 62, '제품 관리부서 E80214 비품 구매사항 비용 청구입니다.', '7bcb2718-8b31-4846-95de-a67d176f186f', '제품 관리부서', 'employee62@gw.com', '사원62', '차장', '제품 관리부서', '사원62', '차장', '010-1234-5661', '제품 관리부서 E80214 비품 구매사항 비용 청구입니다.'),
        (2, 3, 1, 2, '2024-06-23 16:21:17.000000', 0, 7, '2024-06-20 16:21:17.000000', 63, 63, 63, '제품 관리부서 079373 재무 결산입니다.', 'de5cb41a-9abb-4e34-a0d0-b0bc99b30b0b', '제품 관리부서', 'employee63@gw.com', '사원63', '과장', '제품 관리부서', '사원63', '과장', '010-1234-5662', '제품 관리부서 079373 재무 결산입니다.'),
        (2, 3, 1, 2, '2024-05-22 07:00:12.000000', 2400000, 7, '2024-05-19 07:00:12.000000', 64, 64, 64, '제품 관리부서 4A3D2E 재무 결산입니다.', '6a4496cb-90eb-4bd3-b605-2fe3aa3d21bc', '제품 관리부서', 'employee64@gw.com', '사원64', '과장', '제품 관리부서', '사원64', '과장', '010-1234-5663', '제품 관리부서 4A3D2E 재무 결산입니다.'),
        (2, 3, 0, 4, '2024-05-08 17:37:07.000000', 0, 7, '2024-05-05 17:37:07.000000', 65, 65, 65, '제품 관리부서 DC0B6C 비품 구매사항 비용 청구입니다.', '6989ceae-7db5-449b-b9fa-355c8fa6ac59', '제품 관리부서', 'employee65@gw.com', '사원65', '대리', '제품 관리부서', '사원65', '대리', '010-1234-5664', '제품 관리부서 DC0B6C 비품 구매사항 비용 청구입니다.'),
        (2, 3, 2, 6, '2024-01-26 10:15:54.000000', 0, 7, '2024-01-23 10:15:54.000000', 66, 66, 66, '제품 관리부서 7B21DD 문제 발생 관련하여 결재 요청바랍니다.', 'e14096ea-86eb-4d18-ba9d-b2d65bb94c4d', '제품 관리부서', 'employee66@gw.com', '사원66', '대리', '제품 관리부서', '사원66', '대리', '010-1234-5665', '제품 관리부서 7B21DD 문제 발생 관련하여 결재 요청바랍니다.'),
        (1, 3, 1, 2, '2024-02-05 01:05:08.000000', 1200000, 7, '2024-02-02 01:05:08.000000', 67, 67, 67, '제품 관리부서 F24C0E 재무 결산입니다.', 'f6c3f078-3cee-48fc-9700-ea64dfbbd7a1', '제품 관리부서', 'employee67@gw.com', '사원67', '사원', '제품 관리부서', '사원67', '사원', '010-1234-5666', '제품 관리부서 F24C0E 재무 결산입니다.'),
        (1, 3, 2, 5, '2024-01-09 02:38:17.000000', 0, 7, '2024-01-06 02:38:17.000000', 68, 68, 68, '제품 관리부서 3E0345 보고서 관련 결재 요청입니다.', 'fe474267-562b-46c4-84d5-d255e33f3f5f', '제품 관리부서', 'employee68@gw.com', '사원68', '사원', '제품 관리부서', '사원68', '사원', '010-1234-5667', '제품 관리부서 3E0345 보고서 관련 결재 요청입니다.'),
        (0, 3, 2, 5, '2024-06-06 13:25:54.000000', 0, 7, '2024-06-03 13:25:54.000000', 69, 69, 69, '제품 관리부서 CAA5BE 보고서 관련 결재 요청입니다.', 'd2783e34-4b9f-49f1-a02b-646fcbcfaaab', '제품 관리부서', 'employee69@gw.com', '사원69', '사원', '제품 관리부서', '사원69', '사원', '010-1234-5668', '제품 관리부서 CAA5BE 보고서 관련 결재 요청입니다.'),
        (0, 3, 1, 5, '2024-04-09 07:54:57.000000', 0, 7, '2024-04-06 07:54:57.000000', 70, 70, 70, '제품 관리부서 27A44F 보고서 관련 결재 요청입니다.', '96f9a13b-0e06-447a-9a38-99ec0918b5a7', '제품 관리부서', 'employee70@gw.com', '사원70', '사원', '제품 관리부서', '사원70', '사원', '010-1234-5669', '제품 관리부서 27A44F 보고서 관련 결재 요청입니다.'),
        (1, 3, 2, 1, '2024-07-28 07:09:05.000000', 0, 8, '2024-07-25 07:09:05.000000', 71, 71, 71, '고객 서비스부서 190804 업무 기안 결재', 'f91bf8f2-2d69-4ff2-a58e-c68a285a9be2', '고객 서비스부서', 'employee71@gw.com', '사원71', '부장', '고객 서비스부서', '사원71', '부장', '010-1234-5670', '고객 서비스부서 190804 업무 기안 결재'),
        (0, 3, 2, 5, '2024-06-05 02:25:19.000000', 0, 8, '2024-06-02 02:25:19.000000', 72, 72, 72, '고객 서비스부서 8FFBF9 보고서 관련 결재 요청입니다.', 'bfe0b10b-2312-44df-a6f2-68f0c434b4a1', '고객 서비스부서', 'employee72@gw.com', '사원72', '차장', '고객 서비스부서', '사원72', '차장', '010-1234-5671', '고객 서비스부서 8FFBF9 보고서 관련 결재 요청입니다.'),
        (2, 3, 0, 1, '2024-04-06 04:16:56.000000', 0, 8, '2024-04-03 04:16:56.000000', 73, 73, 73, '고객 서비스부서 5E1A87 업무 기안 결재', '5a4c4fed-b972-473d-9c1b-051d229e4796', '고객 서비스부서', 'employee73@gw.com', '사원73', '과장', '고객 서비스부서', '사원73', '과장', '010-1234-5672', '고객 서비스부서 5E1A87 업무 기안 결재'),
        (0, 3, 0, 2, '2024-04-25 21:31:32.000000', 1200000, 8, '2024-04-22 21:31:32.000000', 74, 74, 74, '고객 서비스부서 0CB07F 재무 결산입니다.', '43b8f9dc-5692-4c9f-b18a-3f93d18bbfd4', '고객 서비스부서', 'employee74@gw.com', '사원74', '과장', '고객 서비스부서', '사원74', '과장', '010-1234-5673', '고객 서비스부서 0CB07F 재무 결산입니다.'),
        (3, 3, 1, 2, '2024-06-27 23:11:41.000000', 2400000, 8, '2024-06-24 23:11:41.000000', 75, 75, 75, '고객 서비스부서 81FB9F 재무 결산입니다.', '6d718c1a-1170-49de-aba5-5d909de51d2d', '고객 서비스부서', 'employee75@gw.com', '사원75', '대리', '고객 서비스부서', '사원75', '대리', '010-1234-5674', '고객 서비스부서 81FB9F 재무 결산입니다.'),
        (3, 3, 0, 3, '2024-01-07 20:26:42.000000', 0, 8, '2024-01-04 20:26:42.000000', 76, 76, 76, '고객 서비스부서 2F2CDF 예산 기안 요청', '02a8689b-dcb8-49d3-b732-47b9bfbd6bac', '고객 서비스부서', 'employee76@gw.com', '사원76', '대리', '고객 서비스부서', '사원76', '대리', '010-1234-5675', '고객 서비스부서 2F2CDF 예산 기안 요청'),
        (0, 3, 0, 5, '2024-07-02 06:37:25.000000', 0, 8, '2024-06-29 06:37:25.000000', 77, 77, 77, '고객 서비스부서 425997 보고서 관련 결재 요청입니다.', 'e8f0ad79-8cf5-4eaf-8585-a26ae3ebe73b', '고객 서비스부서', 'employee77@gw.com', '사원77', '사원', '고객 서비스부서', '사원77', '사원', '010-1234-5676', '고객 서비스부서 425997 보고서 관련 결재 요청입니다.'),
        (1, 3, 0, 1, '2024-07-06 07:30:30.000000', 0, 8, '2024-07-03 07:30:30.000000', 78, 78, 78, '고객 서비스부서 06B835 업무 기안 결재', 'f0cb6001-58a1-4a10-a7ca-64b169b677e3', '고객 서비스부서', 'employee78@gw.com', '사원78', '사원', '고객 서비스부서', '사원78', '사원', '010-1234-5677', '고객 서비스부서 06B835 업무 기안 결재'),
        (3, 3, 0, 2, '2024-06-30 17:35:23.000000', 0, 8, '2024-06-27 17:35:23.000000', 79, 79, 79, '고객 서비스부서 C9F028 재무 결산입니다.', 'd546eb8a-2c70-4a24-b02f-0df8b8a61acb', '고객 서비스부서', 'employee79@gw.com', '사원79', '사원', '고객 서비스부서', '사원79', '사원', '010-1234-5678', '고객 서비스부서 C9F028 재무 결산입니다.'),
        (1, 3, 1, 5, '2024-08-05 02:52:51.000000', 0, 8, '2024-08-02 02:52:51.000000', 80, 80, 80, '고객 서비스부서 D4A923 보고서 관련 결재 요청입니다.', '389f3cc4-c58b-4b3f-9068-ef7be2dad220', '고객 서비스부서', 'employee80@gw.com', '사원80', '사원', '고객 서비스부서', '사원80', '사원', '010-1234-5679', '고객 서비스부서 D4A923 보고서 관련 결재 요청입니다.'),
        (1, 3, 2, 1, '2024-01-17 02:44:29.000000', 0, 9, '2024-01-14 02:44:29.000000', 81, 81, 81, '법무부서 4A96B1 업무 기안 결재', 'c7854c85-f0f1-43cd-a03d-c9bdf716a8e4', '법무부서', 'employee81@gw.com', '사원81', '부장', '법무부서', '사원81', '부장', '010-1234-5680', '법무부서 4A96B1 업무 기안 결재'),
        (0, 3, 2, 1, '2024-07-14 10:47:32.000000', 0, 9, '2024-07-11 10:47:32.000000', 82, 82, 82, '법무부서 FED275 업무 기안 결재', 'ff7b0cc5-9500-4309-b2a0-29f37bb705c5', '법무부서', 'employee82@gw.com', '사원82', '차장', '법무부서', '사원82', '차장', '010-1234-5681', '법무부서 FED275 업무 기안 결재'),
        (3, 3, 1, 3, '2024-07-24 21:23:39.000000', 1200000, 9, '2024-07-21 21:23:39.000000', 83, 83, 83, '법무부서 D9637A 예산 기안 요청', '13b1f189-5fa4-4641-b1fe-5b8b53e24de0', '법무부서', 'employee83@gw.com', '사원83', '과장', '법무부서', '사원83', '과장', '010-1234-5682', '법무부서 D9637A 예산 기안 요청'),
        (3, 3, 0, 1, '2024-02-09 21:36:30.000000', 0, 9, '2024-02-06 21:36:30.000000', 84, 84, 84, '법무부서 78AAB1 업무 기안 결재', '0b94461a-c059-4ed0-b8d1-7f51ff874492', '법무부서', 'employee84@gw.com', '사원84', '과장', '법무부서', '사원84', '과장', '010-1234-5683', '법무부서 78AAB1 업무 기안 결재'),
        (3, 3, 1, 1, '2024-02-08 14:11:25.000000', 0, 9, '2024-02-05 14:11:25.000000', 85, 85, 85, '법무부서 2228BB 업무 기안 결재', '1ba10102-be90-444c-a30e-ade07ca0002f', '법무부서', 'employee85@gw.com', '사원85', '대리', '법무부서', '사원85', '대리', '010-1234-5684', '법무부서 2228BB 업무 기안 결재'),
        (2, 3, 2, 2, '2024-07-05 01:09:40.000000', 2400000, 9, '2024-07-02 01:09:40.000000', 86, 86, 86, '법무부서 8D40CC 재무 결산입니다.', '236c7415-4c30-4901-ad3f-4ee6866b0372', '법무부서', 'employee86@gw.com', '사원86', '대리', '법무부서', '사원86', '대리', '010-1234-5685', '법무부서 8D40CC 재무 결산입니다.'),
        (0, 3, 1, 5, '2024-02-13 23:14:48.000000', 0, 9, '2024-02-10 23:14:48.000000', 87, 87, 87, '법무부서 4519F1 보고서 관련 결재 요청입니다.', '177b60be-0dcf-4efb-8e0e-452adb1db371', '법무부서', 'employee87@gw.com', '사원87', '사원', '법무부서', '사원87', '사원', '010-1234-5686', '법무부서 4519F1 보고서 관련 결재 요청입니다.'),
        (2, 3, 0, 4, '2024-06-18 10:58:40.000000', 2400000, 9, '2024-06-15 10:58:40.000000', 88, 88, 88, '법무부서 235697 비품 구매사항 비용 청구입니다.', '6ec7d058-32f9-447f-8d50-7531a08f89e4', '법무부서', 'employee88@gw.com', '사원88', '사원', '법무부서', '사원88', '사원', '010-1234-5687', '법무부서 235697 비품 구매사항 비용 청구입니다.'),
        (2, 3, 2, 3, '2024-03-14 22:57:55.000000', 0, 9, '2024-03-11 22:57:55.000000', 89, 89, 89, '법무부서 0FC0FD 예산 기안 요청', '15f10afa-1110-425e-9803-4ecdd88426de', '법무부서', 'employee89@gw.com', '사원89', '사원', '법무부서', '사원89', '사원', '010-1234-5688', '법무부서 0FC0FD 예산 기안 요청'),
        (3, 3, 0, 5, '2024-01-26 22:40:04.000000', 0, 9, '2024-01-23 22:40:04.000000', 90, 90, 90, '법무부서 D7D20A 보고서 관련 결재 요청입니다.', '0d5786ef-5038-4898-a83a-039c81e38a31', '법무부서', 'employee90@gw.com', '사원90', '사원', '법무부서', '사원90', '사원', '010-1234-5689', '법무부서 D7D20A 보고서 관련 결재 요청입니다.'),
        (1, 3, 0, 4, '2024-05-19 14:09:42.000000', 1200000, 10, '2024-05-16 14:09:42.000000', 91, 91, 91, '연구개발부서 144238 비품 구매사항 비용 청구입니다.', 'c2d16885-0f66-4f9b-be2b-9fe231d07597', '연구개발부서', 'employee91@gw.com', '사원91', '부장', '연구개발부서', '사원91', '부장', '010-1234-5690', '연구개발부서 144238 비품 구매사항 비용 청구입니다.'),
        (0, 3, 1, 5, '2024-08-01 01:48:56.000000', 0, 10, '2024-07-29 01:48:56.000000', 92, 92, 92, '연구개발부서 064C2D 보고서 관련 결재 요청입니다.', 'cc8a63d2-0bff-4fe8-a713-30cc5c2690c4', '연구개발부서', 'employee92@gw.com', '사원92', '차장', '연구개발부서', '사원92', '차장', '010-1234-5691', '연구개발부서 064C2D 보고서 관련 결재 요청입니다.'),
        (3, 3, 0, 6, '2024-02-24 18:34:57.000000', 0, 10, '2024-02-21 18:34:57.000000', 93, 93, 93, '연구개발부서 B7EB9A 문제 발생 관련하여 결재 요청바랍니다.', 'fe8d7983-9ed6-4164-8256-92994ef3705e', '연구개발부서', 'employee93@gw.com', '사원93', '과장', '연구개발부서', '사원93', '과장', '010-1234-5692', '연구개발부서 B7EB9A 문제 발생 관련하여 결재 요청바랍니다.'),
        (1, 3, 1, 2, '2024-01-16 01:32:06.000000', 2400000, 10, '2024-01-13 01:32:06.000000', 94, 94, 94, '연구개발부서 D95B03 재무 결산입니다.', '3a923a8b-af41-4a63-a292-450f5ade319d', '연구개발부서', 'employee94@gw.com', '사원94', '과장', '연구개발부서', '사원94', '과장', '010-1234-5693', '연구개발부서 D95B03 재무 결산입니다.'),
        (1, 3, 2, 1, '2024-03-21 19:28:52.000000', 0, 10, '2024-03-18 19:28:52.000000', 95, 95, 95, '연구개발부서 89F962 업무 기안 결재', '26d1c280-4dba-4aeb-85b2-a3af7c70e7cf', '연구개발부서', 'employee95@gw.com', '사원95', '대리', '연구개발부서', '사원95', '대리', '010-1234-5694', '연구개발부서 89F962 업무 기안 결재'),
        (0, 3, 0, 3, '2024-04-11 02:14:30.000000', 1200000, 10, '2024-04-08 02:14:30.000000', 96, 96, 96, '연구개발부서 723B29 예산 기안 요청', '438d50ac-eba1-40f6-9563-4cff8eafd2ec', '연구개발부서', 'employee96@gw.com', '사원96', '대리', '연구개발부서', '사원96', '대리', '010-1234-5695', '연구개발부서 723B29 예산 기안 요청'),
        (1, 3, 0, 3, '2024-04-06 17:30:09.000000', 1200000, 10, '2024-04-03 17:30:09.000000', 97, 97, 97, '연구개발부서 EB9F11 예산 기안 요청', '04e48517-1d30-4242-9dde-38cba013507d', '연구개발부서', 'employee97@gw.com', '사원97', '사원', '연구개발부서', '사원97', '사원', '010-1234-5696', '연구개발부서 EB9F11 예산 기안 요청'),
        (0, 3, 1, 2, '2024-01-25 22:21:39.000000', 2400000, 10, '2024-01-22 22:21:39.000000', 98, 98, 98, '연구개발부서 735837 재무 결산입니다.', 'd2b03960-eab7-4145-925a-1e8897ac8ecd', '연구개발부서', 'employee98@gw.com', '사원98', '사원', '연구개발부서', '사원98', '사원', '010-1234-5697', '연구개발부서 735837 재무 결산입니다.'),
        (1, 3, 2, 1, '2024-01-15 12:04:54.000000', 0, 10, '2024-01-12 12:04:54.000000', 99, 99, 99, '연구개발부서 CB98E7 업무 기안 결재', '992f297b-c69e-4e82-85ec-1ffb97aaa961', '연구개발부서', 'employee99@gw.com', '사원99', '사원', '연구개발부서', '사원99', '사원', '010-1234-5698', '연구개발부서 CB98E7 업무 기안 결재'),
        (0, 3, 1, 5, '2024-02-08 21:55:29.000000', 0, 10, '2024-02-05 21:55:29.000000', 100, 100, 100, '연구개발부서 EBBCB9 보고서 관련 결재 요청입니다.', '1ba43892-fe45-4777-8110-c288b69cc825', '연구개발부서', 'employee100@gw.com', '사원100', '사원', '연구개발부서', '사원100', '사원', '010-1234-5699', '연구개발부서 EBBCB9 보고서 관련 결재 요청입니다.');

insert into groupworks.workflow_approver (approval, approval_method, approver_type, sequence_num, approval_date, approver_id, employee_id, workflow_id, approver_email, approver_name, approver_phone, approver_rank, comment, department)
values  (1, 1, 1, 1, '2024-07-12 01:35:35.000000', 1, 3, 1, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '결재 승인합니다.', '기술부서'),
        (1, 4, 1, 2, '2024-07-14 01:35:35.000000', 2, 2, 1, 'kah@gw.com', '김아현', '010-1234-5601', '차장', '전결합니다.', '기술부서'),
        (0, 4, 1, 3, null, 3, 1, 1, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-07-12 01:35:35.000000', 4, 5, 1, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-07-12 01:35:35.000000', 5, 6, 1, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-07-12 01:35:35.000000', 6, 7, 1, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 7, 8, 1, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 8, 9, 1, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (0, 1, 1, 1, null, 9, 3, 2, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', null, '기술부서'),
        (0, 1, 1, 2, null, 10, 2, 2, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 1, 1, 3, null, 11, 1, 2, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-02-07 07:44:53.000000', 12, 5, 2, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-02-07 07:44:53.000000', 13, 6, 2, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-02-07 07:44:53.000000', 14, 7, 2, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 15, 8, 2, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 16, 9, 2, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (0, 1, 1, 1, null, 17, 3, 3, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', null, '기술부서'),
        (0, 1, 1, 2, null, 18, 2, 3, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 1, 1, 3, null, 19, 1, 3, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-07-28 05:56:21.000000', 20, 5, 3, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-07-28 05:56:21.000000', 21, 6, 3, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-07-28 05:56:21.000000', 22, 7, 3, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 23, 8, 3, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 24, 9, 3, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (1, 4, 1, 1, '2024-01-11 23:30:42.000000', 25, 3, 4, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '전결합니다.', '기술부서'),
        (0, 4, 1, 2, null, 26, 2, 4, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 4, 1, 3, null, 27, 1, 4, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-01-09 23:30:42.000000', 28, 5, 4, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-01-09 23:30:42.000000', 29, 6, 4, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-01-09 23:30:42.000000', 30, 7, 4, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 31, 8, 4, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 32, 9, 4, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (1, 1, 1, 1, '2024-01-13 13:52:40.000000', 33, 3, 5, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '결재 승인합니다.', '기술부서'),
        (1, 1, 1, 2, '2024-01-13 13:52:40.000000', 34, 2, 5, 'kah@gw.com', '김아현', '010-1234-5601', '차장', '결재 승인합니다.', '기술부서'),
        (1, 4, 1, 3, '2024-01-15 13:52:40.000000', 35, 1, 5, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', '전결합니다.', '기술부서'),
        (1, 0, 2, 1, '2024-01-13 13:52:40.000000', 36, 5, 5, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-01-13 13:52:40.000000', 37, 6, 5, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-01-13 13:52:40.000000', 38, 7, 5, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 39, 8, 5, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 40, 9, 5, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (1, 4, 1, 1, '2024-01-16 05:03:11.000000', 41, 3, 6, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '전결합니다.', '기술부서'),
        (0, 4, 1, 2, null, 42, 2, 6, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 4, 1, 3, null, 43, 1, 6, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-01-14 05:03:11.000000', 44, 5, 6, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-01-14 05:03:11.000000', 45, 6, 6, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-01-14 05:03:11.000000', 46, 7, 6, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 47, 8, 6, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 48, 9, 6, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (2, 5, 1, 1, '2024-02-29 03:34:03.000000', 49, 3, 7, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '반려합니디.', '기술부서'),
        (0, 5, 1, 2, null, 50, 2, 7, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 5, 1, 3, null, 51, 1, 7, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-02-27 03:34:03.000000', 52, 5, 7, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-02-27 03:34:03.000000', 53, 6, 7, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-02-27 03:34:03.000000', 54, 7, 7, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 55, 8, 7, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 56, 9, 7, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (2, 5, 1, 1, '2024-02-01 14:16:47.000000', 57, 3, 8, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '반려합니디.', '기술부서'),
        (0, 5, 1, 2, null, 58, 2, 8, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 5, 1, 3, null, 59, 1, 8, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-01-30 14:16:47.000000', 60, 5, 8, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-01-30 14:16:47.000000', 61, 6, 8, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-01-30 14:16:47.000000', 62, 7, 8, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 63, 8, 8, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 64, 9, 8, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (1, 4, 1, 1, '2024-07-16 02:29:36.000000', 65, 3, 9, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '전결합니다.', '기술부서'),
        (0, 4, 1, 2, null, 66, 2, 9, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 4, 1, 3, null, 67, 1, 9, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-07-14 02:29:36.000000', 68, 5, 9, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-07-14 02:29:36.000000', 69, 6, 9, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-07-14 02:29:36.000000', 70, 7, 9, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 71, 8, 9, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 72, 9, 9, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서'),
        (2, 5, 1, 1, '2024-05-23 12:13:41.000000', 73, 3, 10, 'yhj@gw.com', '유현종', '010-1234-5602', '과장', '반려합니디.', '기술부서'),
        (0, 5, 1, 2, null, 74, 2, 10, 'kah@gw.com', '김아현', '010-1234-5601', '차장', null, '기술부서'),
        (0, 5, 1, 3, null, 75, 1, 10, 'ljm@gw.com', '이정민', '010-1234-5600', '부장', null, '기술부서'),
        (1, 0, 2, 1, '2024-05-21 12:13:41.000000', 76, 5, 10, 'kss@gw.com', '김신실', '010-1234-5604', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 2, '2024-05-21 12:13:41.000000', 77, 6, 10, 'ksy@gw.com', '김소연', '010-1234-5605', '대리', '협의 합니다.', '기술부서'),
        (1, 0, 2, 3, '2024-05-21 12:13:41.000000', 78, 7, 10, 'smj@gw.com', '소민지', '010-1234-5606', '사원', '협의 합니다.', '기술부서'),
        (0, 0, 3, 1, null, 79, 8, 10, 'mjy@gw.com', '문주영', '010-1234-5607', '사원', null, '기술부서'),
        (0, 0, 3, 2, null, 80, 9, 10, 'mjh@gw.com', '마정하', '010-1234-5608', '사원', null, '기술부서');

/* ====== workflow end ======== */


/* ====== Material flow Start ======== */

insert into groupworks.materialflow_business (business_id, address, business_name, business_number, ceo, ceo_tel, fax, item, type)
values  (1, '서울특별시 강남구 테헤란로 123번지 45층', '중앙HTA', '123-45-67900', '최기리', '010-5678-1234', '02-1234-5678', 'IT 교육, 국비훈련', '교육 서비스업'),
        (2, '경기도 이천시 이천동 2000번지 2000-1', '◎◎제약™', '123-45-67890', '홍길동', '010-1234-1325', '031-123-1325', '유제품', '제조업'),
        (3, '서울특별시 강남구 테헤란로 123', '◇◇식품', '124-45-67891', '김영희', '010-5678-1325', '02-567-1325', '음료수', '식품업'),
        (4, '부산광역시 해운대구 우동 1234', '☆☆유통', '125-45-67892', '이철수', '010-8765-4321', '051-876-4321', '가전제품', '유통업'),
        (5, '대구광역시 달서구 상인동 987', '▲▲무역', '126-45-67893', '박영수', '010-4567-7890', '053-456-7890', '자동차 부품', '무역업'),
        (6, '울산광역시 남구 삼산동 456', '♤♤화학', '127-45-67894', '최지우', '010-2345-6789', '052-234-6789', '화학물질', '화학업'),
        (7, '전라북도 전주시 완산구 서노송동 789', '◆◆농산', '128-45-67895', '김민수', '010-3456-7891', '063-345-7891', '농산물', '농업'),
        (8, '경상남도 창원시 마산합포구 중앙동 123', '◇◇기계', '129-45-67896', '이성민', '010-4567-8912', '055-456-8912', '기계부품', '기계업'),
        (9, '경기도 수원시 팔달구 매산로 456', '○○전자', '130-45-67897', '박현준', '010-5678-9123', '031-567-9123', '전자제품', '전자업'),
        (10, '서울특별시 마포구 서교동 789', '△△의료', '131-45-67898', '최예진', '010-6789-1234', '02-678-1234', '의료기기', '의료업'),
        (11, '서울특별시 강남구 테헤란로 123번지 45층', '중앙HTA', '123-45-67900', '최기리', '010-5678-1234', '02-1234-5678', 'IT 교육, 국비훈련', '교육 서비스업'),
        (12, '대전광역시 중구 은행동 123', '▣▣섬유', '132-45-67899', '홍석현', '010-7891-2345', '042-789-2345', '섬유제품', '섬유업'),
        (13, '서울특별시 강남구 서초동 123-45', '삼성전자', '123-45-67890', '이재용', '010-1234-5678', '02-123-4567', '전자제품', '제조업'),
        (14, '서울특별시 영등포구 여의도동 234-56', 'LG화학', '124-45-67891', '신학철', '010-2345-6789', '02-234-5678', '화학물질', '화학업'),
        (15, '서울특별시 서초구 양재동 345-67', '현대자동차', '125-45-67892', '정의선', '010-3456-7890', '02-345-6789', '자동차', '제조업'),
        (16, '경기도 성남시 분당구 불정로 6', '네이버', '126-45-67893', '최수연', '010-4567-8901', '031-456-7890', '인터넷 서비스', '서비스업'),
        (17, '경기도 성남시 분당구 대왕판교로 645번길 16', '카카오', '127-45-67894', '홍은택', '010-5678-9012', '031-567-8901', '모바일 서비스', '정보통신업'),
        (18, '서울특별시 용산구 한강대로 100', '아모레퍼시픽', '128-45-67895', '서경배', '010-6789-0123', '02-678-9012', '화장품', '제조업'),
        (19, '서울특별시 강남구 테헤란로 152', '하이트진로', '129-45-67896', '김인규', '010-7890-1234', '02-789-0123', '음료', '제조업'),
        (20, '경기도 이천시 부발읍 경충대로 2091', 'SK하이닉스', '130-45-67897', '이석희', '010-8901-2345', '031-890-1234', '반도체', '제조업'),
        (21, '서울특별시 중구 동호로 330', 'CJ제일제당', '131-45-67898', '강신호', '010-9012-3456', '02-901-2345', '식품', '제조업'),
        (22, '서울특별시 강서구 하늘길 77', '대한항공', '132-45-67899', '우기홍', '010-0123-4567', '02-012-3456', '항공 서비스', '운송업'),
        (23, 'test test', '페이퍼컴퍼니', '000-00-00001', '나대표', '010-0000-0000', '00-000-000', '종이', '제조업')
        ;

insert into groupworks.material_business_manager (business_id, manager_id, email, name, phone)
values  (23, 1, 'manager01@test.com', '담당자1', '010-0000-0000'),
        (23, 2, 'manager02@test.com', '담당자2', '010-0000-0001'),
        (23, 3, 'manager03@test.com', '담당자3', '010-0000-0003'),
        (23, 4, 'manager04@test.com', '담당자4', '010-0000-0004'),
        (23, 5, 'manager05@test.com', '담당자5', '010-0000-0005'),
        (15, 6, 'momomo@hyundai.com', 'ㅁㅁㅁ', '010-0000-0001'),
        (8, 7, 'triagle@machine.com', '△△△', '010-0000-0001');

insert into groupworks.materialflow_order (delivery_date, due_date, order_date, zip_code, employee_id, manager_id, order_id, tex_amount, total_amount, address, address_detail, classification, order_code)
values  ('2024-08-14', '2024-08-29', '2024-08-07', '00112', 1, 2, 1, 10, 2000, '인천 부평구 무네미로 406 (구산동)', '3번 창고', '수주', '2024081412-0B3760541-I931'),
        ('2024-08-14', '2024-08-22', '2024-08-14', '00002', 1, 3, 2, 10, 17500, '부산 연제구 미남로 1 (거제동)', 'B-7구역', '발주', '2024081414-0A6653603-X773'),
        (null, '2024-09-30', '2024-08-20', '04352', 1, 1, 3, 10, 1135000, '서울 용산구 두텁바위로 2 (남영동)', '제3 출하장', '발주', '2024082014-0A8672909-A423'),
        (null, '2024-09-12', '2024-08-21', '04995', 51, 5, 5, 10, 55100000, '서울 광진구 군자동 48-15', '3층 페이퍼컴퍼니 사무실', '수주', '2024082015-0B9100950-U816'),
        (null, '2024-09-10', '2024-08-27', '08221', 1, 6, 6, 10, 112000000, '서울 구로구 안양천서자전거길 714 (고척동)', '△△상가 지하2층 주차장', '수주', '2024082015-0B2443520-QU02'),
        (null, '2024-09-10', '2024-08-27', '08221', 1, 6, 7, 10, 112000000, '서울 구로구 안양천서자전거길 714 (고척동)', '△△상가 지하2층 주차장', '수주', '2024082015-0B2443520-QU02'),
        (null, '2024-09-22', '2024-09-10', '16072', 21, 7, 8, 30, 2003000000, '경기 의왕시 맑은내길 34-4 (오전동)', '3층 창고', '발주', '2024082015-0A4584109-C0810');

insert into groupworks.materialflow_bom (status, bom_id, order_id, quantity, unit_price, item_code, item_name)
values  (true, 1, 1, 2, 1000, '0B3760541-I931-1', 'A4-200'),
        (false, 2, 2, 5, 980, '0A6653603-X773-1', 'A4-300'),
        (false, 3, 2, 3, 1200, '0A6653603-X773-2', 'A3-200'),
        (true, 4, 2, 3, 3000, '0A6653603-X773-3', 'B3-300'),
        (false, 5, 3, 5, 150000, '0A8672909-A423-1', '고급 가죽 노트'),
        (false, 6, 3, 3, 75000, '0A8672909-A423-2', '프리미엄 펜 세트'),
        (false, 7, 3, 2, 80000, '0A8672909-A423-3', '데스크 오거나이저 세트'),
        (false, 9, 5, 10, 1700000, '0B9100950-U816-1', '고성능 데스크톱 컴퓨터 (i7, RTX 4080, 32GB RAM, 1TB SSD)'),
        (false, 10, 5, 2, 2500000, '0B9100950-U816-2', '서버용 랙 (42U)'),
        (false, 11, 5, 3, 1200000, '0B9100950-U816-3', '사무용 복합기 (프린터/스캐너/복사기 통합)'),
        (false, 12, 5, 2, 500000, '0B9100950-U816-4', '레이저 프린터'),
        (false, 13, 5, 8, 3000000, '0B9100950-U816-5', 'CCTV 시스템 (8채널, 카메라 포함)'),
        (false, 14, 5, 3, 1500000, '0B9100950-U816-6', '회의실용 대형 모니터 (65인치)'),
        (false, 15, 6, 10, 10000000, '0B2443520-QU02-1', '전동 지게차'),
        (false, 16, 6, 4, 3000000, '0B2443520-QU02-2', '소형 트럭'),
        (false, 17, 7, 10, 10000000, '0B2443520-QU02-1', '전동 지게차'),
        (false, 18, 7, 4, 3000000, '0B2443520-QU02-2', '소형 트럭'),
        (false, 19, 8, 3, 130000000, '0A4584109-C0810-1', 'CNC 밀링 머신'),
        (false, 20, 8, 5, 84000000, '0A4584109-C0810-2', '유압프레스'),
        (false, 21, 8, 2, 168000000, '0A4584109-C0810-3', '로봇 용접기'),
        (false, 22, 8, 2, 105000000, '0A4584109-C0810-4', '선반 머신'),
        (false, 23, 8, 3, 56000000, '0A4584109-C0810-5', '기계식 펀칭 머신'),
        (false, 24, 8, 3, 21000000, '0A4584109-C0810-6', '배관 절단기'),
        (false, 25, 8, 5, 48000000, '0A4584109-C0810-7', '컨베이어 벨트 시스템'),
        (false, 26, 8, 5, 17000000, '0A4584109-C0810-8', '산업용 진공 포장기'),
        (false, 27, 8, 3, 7000000, '0A4584109-C0810-9', '제어 패널'),
        (false, 28, 8, 2, 35000000, '0A4584109-C0810-10', '냉각 시스템');

insert into groupworks.materialflow_item (delivery_time, storage_time, bom_id, item_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location)
values  (null, '2024-08-15', 1, 1, '', 'B창고 관리자 ㅁㅁㅁ', '', '0B3760541-I931-115602', '입고', 'B창고'),
        (null, '2024-08-15', 1, 2, '', 'B창고 관리자 ㅁㅁㅁ', '', '0B3760541-I931-125603', '입고', 'B창고'),
        (null, null, 2, 3, null, null, null, '0A6653603-X773-113', null, null),
        (null, null, 2, 4, null, null, null, '0A6653603-X773-124', null, null),
        (null, null, 2, 5, null, null, null, '0A6653603-X773-135', null, null),
        (null, null, 2, 6, null, null, null, '0A6653603-X773-146', null, null),
        (null, null, 2, 7, null, null, null, '0A6653603-X773-157', null, null),
        (null, null, 3, 8, null, null, null, '0A6653603-X773-218', null, null),
        (null, null, 3, 9, null, null, null, '0A6653603-X773-229', null, null),
        (null, null, 3, 10, null, null, null, '0A6653603-X773-2310', null, null),
        ('2024-08-14', null, 4, 11, 'SS운송-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3111', '출고', ''),
        ('2024-08-14', null, 4, 12, 'SS운송-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3212', '출고', ''),
        ('2024-08-14', null, 4, 13, 'SS운송-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3313', '출고', ''),
        (null, null, 5, 14, null, null, null, '0A8672909-A423-1114', null, null),
        (null, null, 5, 15, null, null, null, '0A8672909-A423-1215', null, null),
        (null, null, 5, 16, null, null, null, '0A8672909-A423-1316', null, null),
        (null, null, 5, 17, null, null, null, '0A8672909-A423-1417', null, null),
        (null, null, 5, 18, null, null, null, '0A8672909-A423-1518', null, null),
        (null, null, 6, 19, null, null, null, '0A8672909-A423-2119', null, null),
        (null, null, 6, 20, null, null, null, '0A8672909-A423-2220', null, null),
        (null, null, 6, 21, null, null, null, '0A8672909-A423-2321', null, null),
        (null, null, 7, 22, null, null, null, '0A8672909-A423-3122', null, null),
        (null, null, 7, 23, null, null, null, '0A8672909-A423-3223', null, null),
        (null, null, 9, 63, null, null, null, '0B9100950-U816-1163', null, null),
        (null, null, 9, 64, null, null, null, '0B9100950-U816-1264', null, null),
        (null, null, 9, 65, null, null, null, '0B9100950-U816-1365', null, null),
        (null, null, 9, 66, null, null, null, '0B9100950-U816-1466', null, null),
        (null, null, 9, 67, null, null, null, '0B9100950-U816-1567', null, null),
        (null, null, 9, 68, null, null, null, '0B9100950-U816-1668', null, null),
        (null, null, 9, 69, null, null, null, '0B9100950-U816-1769', null, null),
        (null, null, 9, 70, null, null, null, '0B9100950-U816-1870', null, null),
        (null, null, 9, 71, null, null, null, '0B9100950-U816-1971', null, null),
        (null, null, 9, 72, null, null, null, '0B9100950-U816-11072', null, null),
        (null, null, 10, 73, null, null, null, '0B9100950-U816-2173', null, null),
        (null, null, 10, 74, null, null, null, '0B9100950-U816-2274', null, null),
        (null, null, 11, 75, null, null, null, '0B9100950-U816-3175', null, null),
        (null, null, 11, 76, null, null, null, '0B9100950-U816-3276', null, null),
        (null, null, 11, 77, null, null, null, '0B9100950-U816-3377', null, null),
        (null, null, 12, 78, null, null, null, '0B9100950-U816-4178', null, null),
        (null, null, 12, 79, null, null, null, '0B9100950-U816-4279', null, null),
        (null, null, 13, 80, null, null, null, '0B9100950-U816-5180', null, null),
        (null, null, 13, 81, null, null, null, '0B9100950-U816-5281', null, null),
        (null, null, 13, 82, null, null, null, '0B9100950-U816-5382', null, null),
        (null, null, 13, 83, null, null, null, '0B9100950-U816-5483', null, null),
        (null, null, 13, 84, null, null, null, '0B9100950-U816-5584', null, null),
        (null, null, 13, 85, null, null, null, '0B9100950-U816-5685', null, null),
        (null, null, 13, 86, null, null, null, '0B9100950-U816-5786', null, null),
        (null, null, 13, 87, null, null, null, '0B9100950-U816-5887', null, null),
        (null, null, 14, 88, null, null, null, '0B9100950-U816-6188', null, null),
        (null, null, 14, 89, null, null, null, '0B9100950-U816-6289', null, null),
        (null, null, 14, 90, null, null, null, '0B9100950-U816-6390', null, null),
        (null, null, 15, 91, null, null, null, '0B2443520-QU02-1191', null, null),
        (null, null, 15, 92, null, null, null, '0B2443520-QU02-1292', null, null),
        (null, null, 15, 93, null, null, null, '0B2443520-QU02-1393', null, null),
        (null, null, 15, 94, null, null, null, '0B2443520-QU02-1494', null, null),
        (null, null, 15, 95, null, null, null, '0B2443520-QU02-1595', null, null),
        (null, null, 15, 96, null, null, null, '0B2443520-QU02-1696', null, null),
        (null, null, 15, 97, null, null, null, '0B2443520-QU02-1797', null, null),
        (null, null, 15, 98, null, null, null, '0B2443520-QU02-1898', null, null),
        (null, null, 15, 99, null, null, null, '0B2443520-QU02-1999', null, null),
        (null, null, 15, 100, null, null, null, '0B2443520-QU02-110100', null, null),
        (null, null, 16, 101, null, null, null, '0B2443520-QU02-21101', null, null),
        (null, null, 17, 102, null, null, null, '0B2443520-QU02-11102', null, null),
        (null, null, 16, 103, null, null, null, '0B2443520-QU02-22103', null, null),
        (null, null, 17, 104, null, null, null, '0B2443520-QU02-12104', null, null),
        (null, null, 16, 105, null, null, null, '0B2443520-QU02-23105', null, null),
        (null, null, 16, 106, null, null, null, '0B2443520-QU02-24106', null, null),
        (null, null, 17, 107, null, null, null, '0B2443520-QU02-13107', null, null),
        (null, null, 17, 108, null, null, null, '0B2443520-QU02-14108', null, null),
        (null, null, 17, 109, null, null, null, '0B2443520-QU02-15109', null, null),
        (null, null, 17, 110, null, null, null, '0B2443520-QU02-16110', null, null),
        (null, null, 17, 111, null, null, null, '0B2443520-QU02-17111', null, null),
        (null, null, 17, 112, null, null, null, '0B2443520-QU02-18112', null, null),
        (null, null, 17, 113, null, null, null, '0B2443520-QU02-19113', null, null),
        (null, null, 17, 114, null, null, null, '0B2443520-QU02-110114', null, null),
        (null, null, 18, 115, null, null, null, '0B2443520-QU02-21115', null, null),
        (null, null, 18, 116, null, null, null, '0B2443520-QU02-22116', null, null),
        (null, null, 18, 117, null, null, null, '0B2443520-QU02-23117', null, null),
        (null, null, 18, 118, null, null, null, '0B2443520-QU02-24118', null, null),
        (null, null, 19, 119, null, null, null, '0A4584109-C0810-11119', null, null),
        (null, null, 19, 120, null, null, null, '0A4584109-C0810-12120', null, null),
        (null, null, 19, 121, null, null, null, '0A4584109-C0810-13121', null, null),
        (null, null, 20, 122, null, null, null, '0A4584109-C0810-21122', null, null),
        (null, null, 20, 123, null, null, null, '0A4584109-C0810-22123', null, null),
        (null, null, 20, 124, null, null, null, '0A4584109-C0810-23124', null, null),
        (null, null, 20, 125, null, null, null, '0A4584109-C0810-24125', null, null),
        (null, null, 20, 126, null, null, null, '0A4584109-C0810-25126', null, null),
        (null, null, 21, 127, null, null, null, '0A4584109-C0810-31127', null, null),
        (null, null, 21, 128, null, null, null, '0A4584109-C0810-32128', null, null),
        (null, null, 22, 129, null, null, null, '0A4584109-C0810-41129', null, null),
        (null, null, 22, 130, null, null, null, '0A4584109-C0810-42130', null, null),
        (null, null, 23, 131, null, null, null, '0A4584109-C0810-51131', null, null),
        (null, null, 23, 132, null, null, null, '0A4584109-C0810-52132', null, null),
        (null, null, 23, 133, null, null, null, '0A4584109-C0810-53133', null, null),
        (null, null, 24, 134, null, null, null, '0A4584109-C0810-61134', null, null),
        (null, null, 24, 135, null, null, null, '0A4584109-C0810-62135', null, null),
        (null, null, 24, 136, null, null, null, '0A4584109-C0810-63136', null, null),
        (null, null, 25, 137, null, null, null, '0A4584109-C0810-71137', null, null),
        (null, null, 25, 138, null, null, null, '0A4584109-C0810-72138', null, null),
        (null, null, 25, 139, null, null, null, '0A4584109-C0810-73139', null, null),
        (null, null, 25, 140, null, null, null, '0A4584109-C0810-74140', null, null),
        (null, null, 25, 141, null, null, null, '0A4584109-C0810-75141', null, null),
        (null, null, 26, 142, null, null, null, '0A4584109-C0810-81142', null, null),
        (null, null, 26, 143, null, null, null, '0A4584109-C0810-82143', null, null),
        (null, null, 26, 144, null, null, null, '0A4584109-C0810-83144', null, null),
        (null, null, 26, 145, null, null, null, '0A4584109-C0810-84145', null, null),
        (null, null, 26, 146, null, null, null, '0A4584109-C0810-85146', null, null),
        (null, null, 27, 147, null, null, null, '0A4584109-C0810-91147', null, null),
        (null, null, 27, 148, null, null, null, '0A4584109-C0810-92148', null, null),
        (null, null, 27, 149, null, null, null, '0A4584109-C0810-93149', null, null),
        (null, null, 28, 150, null, null, null, '0A4584109-C0810-101150', null, null),
        (null, null, 28, 151, null, null, null, '0A4584109-C0810-102151', null, null);

insert into groupworks.materialflow_mes (bom_id, defects_num, manufacture_date, order_id, quantity, unit_price, item_code, item_name, process_location)
values  (1, 11, '2024-08-16 17:46:40.659212', 1, 100, 500, '0B3760541-I931-1', 'A4', '공장1 A4 제조 공정'),
        (27, 1, '2024-08-20 16:22:44.721408', 8, 3, 3500000, '0A4584109-C0810-9', '제어 패널', '디스플레이 공장'),
        (28, 1, '2024-08-20 16:27:10.752490', 8, 2, 28000000, '0A4584109-C0810-10', '냉각 시스템', '냉동기계 제조공장'),
        (23, 0, '2024-08-20 16:28:38.658881', 8, 3, 36000000, '0A4584109-C0810-5', '기계식 펀칭 머신', '자동기계 제조공장');

/* ====== Material flow End ======== */

INSERT INTO board (create_date, department_id, employee_id, update_date, content, ip_address, subject, title, board_type) VALUES
    (DATE_SUB(NOW(), INTERVAL 600 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 목표 달성 현황","level":3}},{"id":"2","type":"paragraph","data":{"text":"2024년 목표 달성 현황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 10% 증가","목표 2: 고객 만족도 90% 달성","목표 3: 신규 제품 출시"]}},{"id":"4","type":"paragraph","data":{"text":"현재 달성 상황은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출: 8% 증가","고객 만족도: 85%","신규 제품 출시: 완료"]}}],"version":"2.30.5"}', '192.168.0.1', 'general', '부서 목표 달성 현황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 590 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"고객 피드백 요약","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 고객 피드백을 요약하여 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["긍정적인 피드백: 서비스 품질에 대한 만족도 높음","부정적인 피드백: 배송 지연 문제"]}},{"id":"4","type":"paragraph","data":{"text":"향후 개선 방안은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["배송 프로세스 개선","고객 서비스 교육 강화"]}}],"version":"2.30.5"}', '192.168.0.2', 'suggestion', '고객 피드백 요약', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 580 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"시스템 오류 보고","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음은 최근 시스템 오류에 대한 보고입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["오류 1: 로그인 문제","오류 2: 데이터 백업 실패"]}},{"id":"4","type":"paragraph","data":{"text":"문제 해결을 위한 조치는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["로그인 문제: 시스템 점검 및 업데이트 진행","데이터 백업: 백업 스크립트 점검 및 재설정"]}}],"version":"2.30.5"}', '192.168.0.3', 'error', '시스템 오류 보고', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 570 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 회의 일정 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 팀 회의 일정과 관련된 사항을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["회의 일시: 2024년 8월 30일 10:00 AM","회의 장소: 본사 회의실","회의 안건: 프로젝트 진행 상황 점검"]}},{"id":"4","type":"paragraph","data":{"text":"참석 부탁드립니다."}}],"version":"2.30.5"}', '192.168.0.4', 'announcement', '팀 회의 일정 안내', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 560 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 변경 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음은 최신 정책 변경 사항입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 1: 연차 사용 규정 변경","정책 2: 출근 시간 조정"]}},{"id":"4","type":"paragraph","data":{"text":"정책 변경에 대한 세부 사항은 인사팀에 문의하시기 바랍니다."}}],"version":"2.30.5"}', '192.168.0.5', 'mandatory', '정책 변경 공지', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 550 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"프로젝트 완료 보고","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 완료된 프로젝트에 대한 보고서입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["프로젝트 1: ABC 시스템 구현 완료","프로젝트 2: 고객 포털 리뉴얼 완료"]}},{"id":"4","type":"paragraph","data":{"text":"향후 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["다음 프로젝트: DEF 시스템 개발","업데이트: 고객 피드백 반영"]}}],"version":"2.30.5"}', '192.168.0.6', 'general', '프로젝트 완료 보고', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 540 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀의 최근 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 15% 증가","성과 2: 고객 만족도 92% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"향후 목표는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["목표 1: 매출 20% 증가","목표 2: 고객 만족도 95% 달성"]}}],"version":"2.30.5"}', '192.168.0.7', 'suggestion', '팀 성과 리뷰', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 530 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"고객 요청 사항","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 고객 요청 사항을 정리하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["요청 사항 1: 기능 개선 요청","요청 사항 2: 서비스 확장 요청"]}},{"id":"4","type":"paragraph","data":{"text":"요청 사항에 대한 대응 방안은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["기능 개선: 개발팀에 요청","서비스 확장: 마케팅 팀과 협의"]}}],"version":"2.30.5"}', '192.168.0.8', 'error', '고객 요청 사항', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 520 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서의 최근 성과를 분석합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 110% 달성","성과 2: 고객 만족도 88%"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성과: 목표를 초과 달성하였으나, 고객 만족도는 미비함","고객 피드백 분석: 긍정적인 피드백이 대부분"]}}],"version":"2.30.5"}', '192.168.0.9', 'general', '부서 성과 분석', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 510 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"신규 프로젝트 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음은 신규 프로젝트 제안 사항입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["프로젝트 1: AI 기반 고객 지원 시스템 개발","프로젝트 2: 글로벌 시장 진출 전략 수립"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 피드백은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["AI 프로젝트: 개발팀 검토 후 진행 여부 결정","글로벌 전략: 시장 조사 후 계획 수립"]}}],"version":"2.30.5"}', '192.168.0.10', 'suggestion', '신규 프로젝트 제안', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 500 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"성공 사례 보고","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 성공적인 프로젝트 사례를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["사례 1: 신규 제품 출시 성공","사례 2: 주요 클라이언트 계약 체결"]}},{"id":"4","type":"paragraph","data":{"text":"성과 분석은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["제품 출시: 예상 매출 초과 달성","계약 체결: 장기 계약으로 인한 안정적 매출"]}}],"version":"2.30.5"}', '192.168.0.11', 'general', '성공 사례 보고', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 490 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"작업 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"현재 진행 중인 작업의 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["작업 1: 웹사이트 리뉴얼 - 진행률 70%","작업 2: 서버 업그레이드 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["웹사이트 리뉴얼: 30% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.12', 'announcement', '작업 진행 상황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 480 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀의 목표 달성 현황을 검토합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 연간 매출 20% 증가","목표 2: 고객 피드백 점수 90% 이상 유지"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 증가: 18% 달성","고객 피드백: 87%"]}}],"version":"2.30.5"}', '192.168.0.13', 'general', '팀 목표 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 470 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선을 위한 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 고객 피드백을 반영한 서비스 개선","제안 2: 최신 기술을 활용한 서비스 최적화"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 반영: 서비스 개선 계획 수립","기술 활용: 기술팀과 논의"]}}],"version":"2.30.5"}', '192.168.0.14', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 460 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 성과 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀의 최근 성과를 분석합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 고객 만족도 85%","성과 2: 매출 목표 95% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["고객 만족도: 서비스 개선 필요","매출 목표: 목표 달성 성공"]}}],"version":"2.30.5"}', '192.168.0.15', 'general', '팀 성과 분석', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 450 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"프로젝트 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"현재 진행 중인 프로젝트의 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["프로젝트 1: CRM 시스템 구축 - 진행률 80%","프로젝트 2: 신규 마케팅 캠페인 - 시작 단계"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업과 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["CRM 시스템: 20% 추가 작업 필요","마케팅 캠페인: 전략 수립 후 진행"]}}],"version":"2.30.5"}', '192.168.0.16', 'announcement', '프로젝트 진행 상황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 440 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"고객 요구 사항","level":3}},{"id":"2","type":"paragraph","data":{"text":"최근 고객 요구 사항을 정리하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["요구 사항 1: 기능 개선 요청","요구 사항 2: 서비스 품질 향상 요청"]}},{"id":"4","type":"paragraph","data":{"text":"요구 사항에 대한 대응 방안은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["기능 개선: 개발팀에 전달","서비스 품질 향상: 고객 서비스팀과 협의"]}}],"version":"2.30.5"}', '192.168.0.17', 'error', '고객 요구 사항', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 430 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 1: 업무 효율성 증가","정책 2: 고객 응대 절차 개선"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 효율성: 긍정적인 변화 관찰","고객 응대: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.18', 'mandatory', '정책 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 420 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 현황","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 진행 현황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["개선 사항 1: 사용자 인터페이스 개선","개선 사항 2: 고객 지원 프로세스 개선"]}},{"id":"4","type":"paragraph","data":{"text":"개선 작업 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["인터페이스 개선: 완료","고객 지원 프로세스: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.19', 'suggestion', '서비스 개선 현황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 410 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 연간 매출 15% 증가","목표 2: 고객 만족도 90% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 마케팅 전략 강화","고객 만족도: 서비스 품질 개선"]}}],"version":"2.30.5"}', '192.168.0.20', 'general', '팀 목표 설정', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 400 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 고객 관리 시스템 구축 - 진행률 75%","업무 2: 연간 매출 보고서 작성 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["고객 관리 시스템: 25% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.21', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 390 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 변경 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 변경 사항을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["변경 사항 1: 연차 사용 규정 수정","변경 사항 2: 출근 시간 조정"]}},{"id":"4","type":"paragraph","data":{"text":"변경된 정책에 대한 세부 사항은 인사팀에 문의해 주시기 바랍니다."}}],"version":"2.30.5"}', '192.168.0.22', 'mandatory', '정책 변경 안내', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 380 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 10% 증가","성과 2: 고객 만족도 89%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 증가: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.23', 'general', '팀 성과 리뷰', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 370 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 계획","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선을 위한 계획을 수립하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["계획 1: 고객 피드백 반영","계획 2: 직원 교육 강화"]}},{"id":"4","type":"paragraph","data":{"text":"계획에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 반영: 서비스 개선 계획 수립","직원 교육: 교육 프로그램 개발"]}}],"version":"2.30.5"}', '192.168.0.24', 'suggestion', '서비스 개선 계획', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 360 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 프로세스 점검","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 프로세스를 점검한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["점검 사항 1: 업무 흐름 최적화","점검 사항 2: 업무 지침 재정비"]}},{"id":"4","type":"paragraph","data":{"text":"점검 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 흐름: 최적화 완료","업무 지침: 재정비 필요"]}}],"version":"2.30.5"}', '192.168.0.25', 'announcement', '업무 프로세스 점검', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 350 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 달성 여부","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.26', 'error', '팀 목표 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 340 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 업무 효율성 증가","검토 결과 2: 고객 만족도 향상"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 효율성: 향상됨","고객 만족도: 향상됨"]}}],"version":"2.30.5"}', '192.168.0.27', 'general', '정기 검토 결과', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 330 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI 분석 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["KPI 1: 매출 성장률","KPI 2: 고객 만족도"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 달성","고객 만족도: 목표 미달"]}}],"version":"2.30.5"}', '192.168.0.28', 'general', '부서 KPI 분석', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 320 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 계획","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 계획을 수립하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["계획 1: 사용자 경험 향상","계획 2: 기술 지원 강화"]}},{"id":"4","type":"paragraph","data":{"text":"계획에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["경험 향상: 사용자 인터페이스 개선","기술 지원: 지원 팀 교육 강화"]}}],"version":"2.30.5"}', '192.168.0.29', 'suggestion', '서비스 개선 계획', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 310 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 90% 달성","성과 2: 고객 만족도 85%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 유지"]}}],"version":"2.30.5"}', '192.168.0.30', 'general', '부서 성과 리뷰', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 300 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 회의","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 회의 일정을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["회의 1: 다음 주 월요일 오전 10시","회의 2: 프로젝트 진행 상황 리뷰"]}}],"version":"2.30.5"}', '192.168.0.31', 'announcement', '정기 회의', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 290 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 현황","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 현황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["현황 1: 고객 피드백 반영 완료","현황 2: 기술 지원 강화 진행 중"]}},{"id":"4","type":"paragraph","data":{"text":"현황에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 반영: 완료","기술 지원: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.32', 'announcement', '서비스 개선 현황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 280 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 분석","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 달성","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.33', 'general', '팀 목표 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 270 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 프로세스 개선","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 프로세스 개선을 위한 계획을 수립하였습니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["계획 1: 업무 흐름 최적화","계획 2: 업무 지침 재정비"]}},{"id":"4","type":"paragraph","data":{"text":"계획에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 흐름: 최적화 계획 수립","업무 지침: 재정비 중"]}}],"version":"2.30.5"}', '192.168.0.34', 'suggestion', '업무 프로세스 개선', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 260 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 달성","목표 2: 고객 만족도 90% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 마케팅 전략 강화","고객 만족도: 서비스 품질 개선"]}}],"version":"2.30.5"}', '192.168.0.35', 'general', '팀 목표 설정', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 250 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 1: 직원 복지 개선","정책 2: 업무 절차 간소화"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["복지 개선: 긍정적인 변화 관찰","절차 간소화: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.36', 'general', '정책 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 240 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 분석한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 성장률 8%","성과 2: 고객 만족도 85%"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.37', 'general', '부서 성과 분석', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 230 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 고객 피드백 수집 및 분석","제안 2: 서비스 개선 팀 구성"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["피드백 수집: 진행 중","서비스 개선 팀: 구성 완료"]}}],"version":"2.30.5"}', '192.168.0.38', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 220 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 100% 달성","목표 2: 고객 만족도 95% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 신제품 출시","고객 만족도: 고객 지원 강화"]}}],"version":"2.30.5"}', '192.168.0.39', 'general', '팀 목표 설정', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 210 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 업무 프로세스 개선","검토 결과 2: 고객 만족도 향상"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 프로세스 개선: 완료","고객 만족도: 향상됨"]}}],"version":"2.30.5"}', '192.168.0.40', 'general', '정기 검토 결과', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 200 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 시스템 구축 - 진행률 80%","업무 2: 기존 시스템 유지보수 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["시스템 구축: 20% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.41', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 190 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["KPI 1: 매출 성장률 7%","KPI 2: 고객 만족도 80%"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.42', 'general', '부서 KPI 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 180 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 프로세스 점검","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 프로세스 점검 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["점검 사항 1: 업무 흐름 분석","점검 사항 2: 업무 지침 검토"]}},{"id":"4","type":"paragraph","data":{"text":"점검 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["업무 흐름: 분석 완료","업무 지침: 검토 중"]}}],"version":"2.30.5"}', '192.168.0.43', 'announcement', '업무 프로세스 점검', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 170 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 서비스 제공 방식 개선","제안 2: 고객 피드백 적극 반영"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 제공 방식: 개선 중","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.44', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 160 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 85% 달성","성과 2: 고객 만족도 82%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 개선 필요"]}}],"version":"2.30.5"}', '192.168.0.45', 'general', '부서 성과 리뷰', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 150 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정책 변경 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"정책 변경 사항을 안내드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["변경 사항 1: 복리후생 개선","변경 사항 2: 근무 시간 조정"]}},{"id":"4","type":"paragraph","data":{"text":"변경된 정책에 대한 세부 사항은 인사팀에 문의해 주시기 바랍니다."}}],"version":"2.30.5"}', '192.168.0.46', 'mandatory', '정책 변경 안내', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 140 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 분석","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI를 분석한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["KPI 1: 매출 성장률 9%","KPI 2: 고객 만족도 83%"]}},{"id":"4","type":"paragraph","data":{"text":"분석 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.47', 'general', '부서 KPI 분석', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 130 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 달성 여부","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 달성","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.48', 'general', '팀 목표 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 120 MINUTE), 1, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 시스템 개발 - 진행률 60%","업무 2: 기존 시스템 유지보수 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["신규 시스템 개발: 40% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.49', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 110 MINUTE), 1, 10, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 서비스 품질 개선","검토 결과 2: 고객 피드백 반영"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 품질: 개선됨","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.50', 'general', '정기 검토 결과', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 100 MINUTE), 1, 1, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 90% 달성","목표 2: 고객 만족도 88% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 판매 촉진 활동","고객 만족도: 고객 서비스 개선"]}}],"version":"2.30.5"}', '192.168.0.51', 'general', '팀 목표 설정', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 90 MINUTE), 1, 2, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"서비스 개선 제안","level":3}},{"id":"2","type":"paragraph","data":{"text":"서비스 개선 제안을 드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["제안 1: 서비스 제공 방식 개선","제안 2: 고객 피드백 적극 반영"]}},{"id":"4","type":"paragraph","data":{"text":"제안에 대한 검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 제공 방식: 개선 중","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.52', 'suggestion', '서비스 개선 제안', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 80 MINUTE), 1, 3, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 KPI 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 KPI를 검토한 결과를 보고드립니다."}},{"id":"3","data":{"style":"unordered","items":["KPI 1: 매출 성장률 6%","KPI 2: 고객 만족도 78%"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 성장률: 목표 미달","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.53', 'general', '부서 KPI 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 70 MINUTE), 1, 4, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"업무 진행 상황","level":3}},{"id":"2","type":"paragraph","data":{"text":"업무 진행 상황을 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["업무 1: 신규 시스템 개발 - 진행률 50%","업무 2: 기존 시스템 유지보수 - 완료"]}},{"id":"4","type":"paragraph","data":{"text":"남은 작업은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["신규 시스템 개발: 50% 추가 작업 필요"]}}],"version":"2.30.5"}', '192.168.0.54', 'announcement', '업무 진행 상황', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 60 MINUTE), 1, 5, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 검토","level":3}},{"id":"2","type":"paragraph","data":{"text":"팀 목표를 검토한 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 사항 1: 매출 목표 분석","검토 사항 2: 고객 만족도 분석"]}},{"id":"4","type":"paragraph","data":{"text":"검토 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 달성","고객 만족도: 향상 필요"]}}],"version":"2.30.5"}', '192.168.0.55', 'general', '팀 목표 검토', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 50 MINUTE), 1, 6, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"정기 검토 결과","level":3}},{"id":"2","type":"paragraph","data":{"text":"정기 검토 결과를 보고드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["검토 결과 1: 서비스 품질 향상","검토 결과 2: 고객 피드백 반영"]}},{"id":"4","type":"paragraph","data":{"text":"결과에 대한 세부 사항은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["서비스 품질: 향상됨","피드백 반영: 진행 중"]}}],"version":"2.30.5"}', '192.168.0.56', 'general', '정기 검토 결과', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 40 MINUTE), 1, 7, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"부서 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서 성과를 리뷰합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["성과 1: 매출 목표 95% 달성","성과 2: 고객 만족도 87%"]}},{"id":"4","type":"paragraph","data":{"text":"리뷰 결과는 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 목표 미달","고객 만족도: 유지"]}}],"version":"2.30.5"}', '192.168.0.57', 'general', '부서 성과 리뷰', 'DEPARTMENT'),
    (DATE_SUB(NOW(), INTERVAL 30 MINUTE), 1, 8, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"팀 목표 설정","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 분기의 팀 목표를 설정합니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["목표 1: 매출 목표 100% 달성","목표 2: 고객 만족도 90% 달성"]}},{"id":"4","type":"paragraph","data":{"text":"목표 달성을 위한 계획은 다음과 같습니다."}},{"id":"5","type":"list","data":{"style":"unordered","items":["매출 목표: 마케팅 강화","고객 만족도: 서비스 개선"]}}],"version":"2.30.5"}', '192.168.0.58', 'general', '팀 목표 설정', 'DEPARTMENT');


INSERT INTO board (create_date, department_id, employee_id, update_date, content, ip_address, subject, title, board_type) VALUES
    (DATE_SUB(NOW(), INTERVAL 600 MINUTE), null, 9, NULL, '{"time":1724201390946,"blocks":[{"id":"1","type":"header","data":{"text":"사내 워크숍 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 주 금요일, 사내 워크숍이 진행됩니다. 모든 직원은 참석해주시기 바랍니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 30일 오전 10시","장소: 본사 3층 회의실","준비물: 필기구"]}}],"version":"2.30.5"}', '192.168.0.1', 'announcement', '사내 워크숍 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 550 MINUTE), null, 8, NULL, '{"time":1724201390947,"blocks":[{"id":"1","type":"header","data":{"text":"부서 점심 모임","level":3}},{"id":"2","type":"paragraph","data":{"text":"이번 주 목요일, 12시에 부서 점심 모임이 있습니다. 참석 여부를 알려주시기 바랍니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 23일 오후 12시","장소: 사내 카페테리아","특이사항: 알레르기 정보 제공"]}}],"version":"2.30.5"}', '192.168.0.2', 'announcement', '부서 점심 모임 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 500 MINUTE), null, 7, NULL, '{"time":1724201390948,"blocks":[{"id":"1","type":"header","data":{"text":"연말 파티 일정","level":3}},{"id":"2","type":"paragraph","data":{"text":"연말 파티가 12월 20일 저녁 7시에 진행됩니다. 연말 분위기를 만끽할 수 있는 자리가 될 것입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 12월 20일 오후 7시","장소: 본사 1층 대연회장","복장: 정장"]}}],"version":"2.30.5"}', '192.168.0.3', 'announcement', '연말 파티 일정 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 450 MINUTE), null, 6, NULL, '{"time":1724201390949,"blocks":[{"id":"1","type":"header","data":{"text":"업무 시스템 점검 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"시스템 점검이 8월 25일 일요일 진행됩니다. 점검 시간 동안 시스템 사용이 제한됩니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["점검일시: 2024년 8월 25일 오전 2시 - 오전 6시","영향: 전사 시스템 사용 불가","대책: 업무 중인 경우 미리 작업 저장"]}}],"version":"2.30.5"}', '192.168.0.4', 'announcement', '업무 시스템 점검 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 400 MINUTE), null, 5, NULL, '{"time":1724201390950,"blocks":[{"id":"1","type":"header","data":{"text":"신규 복지 정책 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"새로운 복지 정책이 도입됩니다. 자세한 내용은 첨부된 문서를 참조해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 내용: 건강 검진 지원, 체력 단련비 지원","적용일: 2024년 9월 1일","문의: 인사팀"]}}],"version":"2.30.5"}', '192.168.0.5', 'announcement', '신규 복지 정책 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 350 MINUTE), null, 4, NULL, '{"time":1724201390951,"blocks":[{"id":"1","type":"header","data":{"text":"사내 장비 교체 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"오는 9월 1일부터 사내 장비 교체 작업이 진행됩니다. 업무에 참고해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["교체 대상: 노트북, 데스크탑","교체 일정: 2024년 9월 1일 - 9월 5일","영향: 일부 부서의 장비 사용 제한"]}}],"version":"2.30.5"}', '192.168.0.6', 'announcement', '사내 장비 교체 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 300 MINUTE), null, 3, NULL, '{"time":1724201390952,"blocks":[{"id":"1","type":"header","data":{"text":"건강 세미나 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"건강 세미나가 9월 10일에 진행됩니다. 건강 관리에 대한 유익한 정보를 제공할 예정입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 10일 오후 2시","장소: 본사 2층 세미나실","강사: 건강 전문가 김철수"]}}],"version":"2.30.5"}', '192.168.0.7', 'announcement', '건강 세미나 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 250 MINUTE), null, 3, NULL, '{"time":1724201390953,"blocks":[{"id":"1","type":"header","data":{"text":"사내 헬스장 오픈","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 헬스장이 9월 15일에 오픈합니다. 많은 이용 바랍니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 15일","장소: 본사 4층","운영시간: 월~금, 오전 8시~오후 8시"]}}],"version":"2.30.5"}', '192.168.0.8', 'announcement', '사내 헬스장 오픈 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 200 MINUTE), null, 3, NULL, '{"time":1724201390954,"blocks":[{"id":"1","type":"header","data":{"text":"사내 서류 정리 일정","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 서류 정리 작업이 8월 28일에 진행됩니다. 서류 정리에 협조 부탁드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 28일","장소: 각 부서 회의실","영향: 서류 접근 일시 제한"]}}],"version":"2.30.5"}', '192.168.0.9', 'announcement', '사내 서류 정리 일정', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 150 MINUTE), null, 3, NULL, '{"time":1724201390955,"blocks":[{"id":"1","type":"header","data":{"text":"팀 빌딩 활동 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"다음 주 월요일 팀 빌딩 활동이 진행됩니다. 참여를 통해 팀워크를 다져보세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 26일 오후 3시","장소: 사내 운동장","활동: 팀 대항 게임"]}}],"version":"2.30.5"}', '192.168.0.10', 'announcement', '팀 빌딩 활동 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 100 MINUTE), null, 2, NULL, '{"time":1724201390956,"blocks":[{"id":"1","type":"header","data":{"text":"고객 피드백 세션 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"고객 피드백 세션이 8월 29일에 진행됩니다. 고객의 소리를 들을 수 있는 기회가 될 것입니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 29일 오후 2시","장소: 본사 1층 대회의실","참석대상: 전직원"]}}],"version":"2.30.5"}', '192.168.0.11', 'announcement', '고객 피드백 세션 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 50 MINUTE), null, 2, NULL, '{"time":1724201390957,"blocks":[{"id":"1","type":"header","data":{"text":"사내 책 클럽 모임","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 책 클럽 모임이 8월 31일에 열립니다. 관심 있는 분들은 참석해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 31일 오후 4시","장소: 본사 5층 독서실","토론 주제: 최근 읽은 책"]}}],"version":"2.30.5"}', '192.168.0.12', 'announcement', '사내 책 클럽 모임 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 30 MINUTE), null, 2, NULL, '{"time":1724201390958,"blocks":[{"id":"1","type":"header","data":{"text":"사내 보안 교육 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 보안 교육이 9월 5일에 진행됩니다. 모든 직원은 필히 참석해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 5일 오전 10시","장소: 본사 6층 세미나실","강사: 보안 전문가 이민호"]}}],"version":"2.30.5"}', '192.168.0.13', 'announcement', '사내 보안 교육 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 10 MINUTE), null, 2, NULL, '{"time":1724201390959,"blocks":[{"id":"1","type":"header","data":{"text":"연구개발팀 회의 일정","level":3}},{"id":"2","type":"paragraph","data":{"text":"연구개발팀의 정기 회의가 8월 22일에 있습니다. 참여해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 22일 오전 9시","장소: 연구개발팀 회의실","안건: 프로젝트 진행 상황"]}}],"version":"2.30.5"}', '192.168.0.14', 'announcement', '연구개발팀 회의 일정', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 5 MINUTE), null, 2, NULL, '{"time":1724201390960,"blocks":[{"id":"1","type":"header","data":{"text":"사내 채용 공고","level":3}},{"id":"2","type":"paragraph","data":{"text":"신규 채용 공고가 게시되었습니다. 관심 있는 분들은 확인해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["직무: 마케팅 담당","자격요건: 3년 이상의 경력","접수 마감일: 2024년 9월 10일"]}}],"version":"2.30.5"}', '192.168.0.15', 'announcement', '사내 채용 공고', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 50 MINUTE), null, 1, NULL, '{"time":1724201390961,"blocks":[{"id":"1","type":"header","data":{"text":"IT 장비 업데이트 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"IT 장비 업데이트가 8월 27일에 진행됩니다. 업무에 참고해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 27일 오전 9시","장소: IT 부서","영향: 일부 시스템 다운"]}}],"version":"2.30.5"}', '192.168.0.16', 'announcement', 'IT 장비 업데이트 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 40 MINUTE), null, 1, NULL, '{"time":1724201390962,"blocks":[{"id":"1","type":"header","data":{"text":"부서별 성과 리뷰","level":3}},{"id":"2","type":"paragraph","data":{"text":"부서별 성과 리뷰가 8월 30일에 진행됩니다. 모든 부서는 준비해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 30일 오후 2시","장소: 본사 3층 대회의실","참석자: 각 부서장"]}}],"version":"2.30.5"}', '192.168.0.17', 'announcement', '부서별 성과 리뷰', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 30 MINUTE), null, 1, NULL, '{"time":1724201390963,"blocks":[{"id":"1","type":"header","data":{"text":"사내 휴가 정책 안내","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 휴가 정책이 변경되었습니다. 새로운 정책을 확인해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["정책 변경일: 2024년 9월 1일","주요 변경 사항: 휴가 신청 절차 간소화","문의: 인사팀"]}}],"version":"2.30.5"}', '192.168.0.18', 'announcement', '사내 휴가 정책 안내', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 20 MINUTE), null, 1, NULL, '{"time":1724201390964,"blocks":[{"id":"1","type":"header","data":{"text":"전사 회식 공지","level":3}},{"id":"2","type":"paragraph","data":{"text":"전사 회식이 9월 7일에 진행됩니다. 많은 참여 부탁드립니다."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 9월 7일 오후 6시","장소: 사내 식당","특이사항: 식사 전 회의"]}}],"version":"2.30.5"}', '192.168.0.19', 'announcement', '전사 회식 공지', 'NOTICE'),
    (DATE_SUB(NOW(), INTERVAL 10 MINUTE), null, 1, NULL, '{"time":1724201390965,"blocks":[{"id":"1","type":"header","data":{"text":"사내 환경 미화 활동","level":3}},{"id":"2","type":"paragraph","data":{"text":"사내 환경 미화 활동이 8월 29일에 진행됩니다. 모든 직원이 참여해 주세요."}},{"id":"3","type":"list","data":{"style":"unordered","items":["일시: 2024년 8월 29일 오전 10시","장소: 본사 전역","준비물: 장갑, 청소 도구"]}}],"version":"2.30.5"}', '192.168.0.20', 'announcement', '사내 환경 미화 활동', 'NOTICE');

INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 1, '2024-08-21 10:29:57.484617', NULL, '매출 목표와 고객 만족도 목표 모두 매우 도전적입니다! 👍 특히 매출 목표를 달성하기 위해 마케팅을 강화하는 것은 좋은 접근입니다. 이를 위해 구체적인 마케팅 전략과 캠페인 계획이 필요할 것 같습니다. 추가적으로, 고객 만족도 90% 달성을 위한 서비스 개선 방안도 세부적으로 검토해볼 필요가 있을 것 같습니다. 이를 위해 고객 피드백을 적극적으로 반영하는 것이 중요할 것 같아요.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 2, '2024-08-21 10:30:06.083897', NULL, '팀 목표 설정을 잘하셨습니다. 매출 목표와 고객 만족도 목표는 명확하고 실현 가능한 목표로 보입니다. 고객 만족도를 높이기 위한 서비스 개선은 고객의 목소리를 직접 듣고 반영하는 것이 핵심이겠죠? 이를 위해 정기적인 고객 피드백 세션을 계획하고, 고객의 불만 사항을 신속히 해결할 수 있는 시스템을 구축하면 좋을 것 같습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 3, '2024-08-21 10:30:16.144943', NULL, '매출 목표 100% 달성과 고객 만족도 90%는 모두 중요한 목표입니다. 매출 목표를 달성하기 위한 마케팅 강화 계획은 좋지만, 예상되는 도전 과제나 문제점을 미리 파악하고 대응 전략을 세우는 것도 도움이 될 것입니다. 또한, 서비스 개선을 위한 구체적인 액션 플랜을 수립하여 팀원들이 각자의 역할을 명확히 이해하고 실행할 수 있도록 하는 것이 중요합니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 4, '2024-08-21 10:30:18.311632', NULL, '목표 설정이 매우 명확합니다. 목표 달성을 위해 계획된 마케팅 강화와 서비스 개선이 성공적으로 진행되기를 바랍니다. 이를 위해 각 팀원들이 맡은 바 역할을 충실히 수행할 수 있도록 지원할 방안도 고려해야 할 것 같습니다. 예를 들어, 마케팅 팀과 고객 서비스 팀 간의 협업을 강화하는 방안을 고민해보는 것도 좋을 것 같습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 5, '2024-08-21 10:30:31.591583', NULL, '팀 목표 설정에 대한 계획이 잘 되어 있습니다. 목표 달성을 위해 각 목표에 대해 구체적인 KPI를 설정하고, 이를 지속적으로 모니터링하는 것이 중요할 것 같습니다. 목표 달성 상황을 주기적으로 점검하고, 필요 시 계획을 수정하여 유연하게 대응하는 것도 효과적일 것입니다. 모든 팀원이 목표를 공유하고 함께 노력하면 좋은 결과를 얻을 수 있을 것입니다.');
INSERT INTO comment (board_id, author_id, created_date,  parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:30:48.097925', 1, '좋은 피드백 감사합니다! 매출 목표 달성을 위한 마케팅 전략은 현재 두 가지 주요 캠페인을 계획 중에 있습니다. 하나는 소셜 미디어 광고 강화이고, 다른 하나는 이메일 마케팅 캠페인입니다. 고객 만족도 90% 달성을 위해서는 고객 피드백을 직접 반영할 수 있는 시스템을 구축하고, 이를 통해 얻은 데이터를 분석하여 구체적인 개선안을 도출할 예정입니다. 추가적인 제안이 있으면 언제든지 말씀해 주세요!');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:30:59.776921', 2, '소중한 의견 감사합니다. 맞습니다, 고객 피드백을 적극적으로 반영하는 것이 중요하죠. 고객의 의견을 수집하는 방법으로는 설문조사와 직접 인터뷰를 고려하고 있습니다. 이를 통해 서비스 개선 방향을 명확히 하고, 실질적인 문제 해결 방안을 마련할 수 있을 것입니다. 피드백 세션의 구체적인 일정이나 방법에 대해 더 논의해보면 좋겠습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:31:08.383114', 3, '말씀하신 대로, 도전 과제를 사전에 파악하고 대응 전략을 마련하는 것이 중요하다는 점에 동의합니다. 특히 마케팅 강화 측면에서, 경쟁사 분석과 고객 행동 분석을 통해 예상되는 문제점을 미리 식별하고 대비할 계획입니다. 서비스 개선의 경우, 고객의 불만 사항을 빠르게 해결할 수 있는 시스템을 구축하여 문제가 발생했을 때 신속히 대응할 수 있도록 하겠습니다. 좋은 제안 감사합니다!');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUE
    (58, 8, '2024-08-21 10:31:14.823746', 4, '팀 간 협업 강화에 대한 의견도 감사합니다. 마케팅 팀과 고객 서비스 팀 간의 협력을 통해 더 나은 결과를 얻을 수 있을 것이라 생각합니다. 이를 위해 정기적인 협업 미팅과 공동 프로젝트를 통해 두 팀 간의 소통을 강화할 계획입니다. 각 팀의 역할과 책임을 명확히 하고, 협업의 효율성을 높이기 위한 방안을 마련해보겠습니다.');
INSERT INTO comment (board_id, author_id, created_date, parent_comment_id, content) VALUES
    (58, 8, '2024-08-21 10:31:20.584126', 5, '정확한 지적 감사합니다. KPI 설정과 지속적인 모니터링은 목표 달성의 핵심이죠. KPI를 설정할 때, 구체적이고 측정 가능한 지표를 설정하여 목표 달성 상황을 명확히 파악할 수 있도록 하겠습니다. 주기적인 점검과 필요시 계획 수정에 대한 의견도 적극 반영하여, 팀원들이 목표에 집중할 수 있도록 지원하겠습니다. 의견 주셔서 감사합니다!');


INSERT INTO chat_room(name) values ('전체공지');
INSERT INTO chat_room_participants (chat_room_id, participant_id)
SELECT 1, employee_id
FROM employee;

INSERT INTO chat_room(name) values ('부서채팅');
INSERT INTO chat_room_participants (chat_room_id, participant_id)
SELECT 2, employee_id
FROM employee WHERE department_id = 1;

INSERT INTO attendance (overtime_hours, work_hours, clock_in_time, clock_out_time, date, employee_id, status)
VALUES
    (0, 540, '2024-07-01 09:00:00.388684', '2024-07-01 18:00:00.388684', '2024-07-01 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-02 09:00:00.388684', '2024-07-02 18:00:00.388684', '2024-07-02 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-03 09:00:00.388684', '2024-07-03 18:00:00.388684', '2024-07-03 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-04 09:00:00.388684', '2024-07-04 18:00:00.388684', '2024-07-04 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-05 09:00:00.388684', '2024-07-05 18:00:00.388684', '2024-07-05 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-08 09:00:00.388684', '2024-07-08 18:00:00.388684', '2024-07-08 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-09 09:00:00.388684', '2024-07-09 18:00:00.388684', '2024-07-09 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-10 09:00:00.388684', '2024-07-10 18:00:00.388684', '2024-07-10 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-11 09:00:00.388684', '2024-07-11 18:00:00.388684', '2024-07-11 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-12 09:00:00.388684', '2024-07-12 18:00:00.388684', '2024-07-12 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-15 09:00:00.388684', '2024-07-15 18:00:00.388684', '2024-07-15 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-16 09:00:00.388684', '2024-07-16 18:00:00.388684', '2024-07-16 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-17 09:00:00.388684', '2024-07-17 18:00:00.388684', '2024-07-17 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-18 09:00:00.388684', '2024-07-18 18:00:00.388684', '2024-07-18 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-19 09:00:00.388684', '2024-07-19 18:00:00.388684', '2024-07-19 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-22 09:00:00.388684', '2024-07-22 18:00:00.388684', '2024-07-22 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-23 09:00:00.388684', '2024-07-23 18:00:00.388684', '2024-07-23 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-24 09:00:00.388684', '2024-07-24 18:00:00.388684', '2024-07-24 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-25 09:00:00.388684', '2024-07-25 18:00:00.388684', '2024-07-25 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-26 09:00:00.388684', '2024-07-26 18:00:00.388684', '2024-07-26 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-29 09:00:00.388684', '2024-07-29 18:00:00.388684', '2024-07-29 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-30 09:00:00.388684', '2024-07-30 18:00:00.388684', '2024-07-30 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-07-31 09:00:00.388684', '2024-07-31 18:00:00.388684', '2024-07-31 18:00:00.388684', 1, '🌟 출퇴근 완료');

INSERT INTO attendance (overtime_hours, work_hours, clock_in_time, clock_out_time, date, employee_id, status)
VALUES
    (0, 540, '2024-08-01 09:00:00.388684', '2024-08-01 18:00:00.388684', '2024-08-01 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-02 09:00:00.388684', '2024-08-02 18:00:00.388684', '2024-08-02 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-05 09:00:00.388684', '2024-08-05 18:00:00.388684', '2024-08-05 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-06 09:00:00.388684', '2024-08-06 18:00:00.388684', '2024-08-06 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (120, 540, '2024-08-07 09:00:00.388684', '2024-08-07 20:00:00.388684', '2024-08-07 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-08 09:00:00.388684', '2024-08-08 18:00:00.388684', '2024-08-08 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-09 09:00:00.388684', '2024-08-09 18:00:00.388684', '2024-08-09 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-12 09:00:00.388684', '2024-08-12 18:00:00.388684', '2024-08-12 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (180, 540, '2024-08-14 09:00:00.388684', '2024-08-14 21:00:00.388684', '2024-08-14 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-15 09:00:00.388684', '2024-08-15 18:00:00.388684', '2024-08-15 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-16 09:00:00.388684', '2024-08-16 18:00:00.388684', '2024-08-16 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-17 09:00:00.388684', '2024-08-17 18:00:00.388684', '2024-08-17 18:00:00.388684', 1, '🌟 출퇴근 완료'),
    (0, 540, '2024-08-20 09:00:00.388684', '2024-08-20 18:00:00.388684', '2024-08-20 18:00:00.388684', 1, '🌟 출퇴근 완료');
