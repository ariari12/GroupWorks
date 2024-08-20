
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



/* ====== workflow start ======== */


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
        (23, 5, 'manager05@test.com', '담당자5', '010-0000-0005')
        ;

insert into groupworks.materialflow_order (delivery_date, due_date, order_date, zip_code, employee_id, manager_id, order_id, tex_amount, total_amount, address, address_detail, classification, order_code)
values  ('2024-08-14', '2024-08-29', '2024-08-07', '00112', 1, 2, 1, 10, 2000, 'test', 'test', '수주', '2024081412-0B3760541-I931'),
        ('2024-08-14', '2024-08-22', '2024-08-14', '00002', 1, 3, 2, 10, 17500, 'test', 'test', '발주', '2024081414-0A6653603-X773')
        ;

insert into groupworks.materialflow_bom (status, bom_id, order_id, quantity, unit_price, item_code, item_name)
values  (true, 1, 1, 2, 1000, '0B3760541-I931-1', 'A4-200'),
        (false, 2, 2, 5, 980, '0A6653603-X773-1', 'A4-300'),
        (false, 3, 2, 3, 1200, '0A6653603-X773-2', 'A3-200'),
        (true, 4, 2, 3, 3000, '0A6653603-X773-3', 'B3-300')
        ;

insert into groupworks.materialflow_item (delivery_time, storage_time, bom_id, item_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location)
values  (null, '2024-08-15', 1, 1, null, '', 'test-a', '', '0B3760541-I931-115602', '입고', 'test-b'),
        (null, '2024-08-15', 1, 2, null, '', 'test-a', '', '0B3760541-I931-125603', '입고', 'test-b'),
        (null, null, 2, 3, null, null, null, null, '0A6653603-X773-113', null, null),
        (null, null, 2, 4, null, null, null, null, '0A6653603-X773-124', null, null),
        (null, null, 2, 5, null, null, null, null, '0A6653603-X773-135', null, null),
        (null, null, 2, 6, null, null, null, null, '0A6653603-X773-146', null, null),
        (null, null, 2, 7, null, null, null, null, '0A6653603-X773-157', null, null),
        (null, null, 3, 8, null, null, null, null, '0A6653603-X773-218', null, null),
        (null, null, 3, 9, null, null, null, null, '0A6653603-X773-229', null, null),
        (null, null, 3, 10, null, null, null, null, '0A6653603-X773-2310', null, null),
        ('2024-08-14', null, 4, 11, null, 'SS택배-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3111', '출고', ''),
        ('2024-08-14', null, 4, 12, null, 'SS택배-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3212', '출고', ''),
        ('2024-08-14', null, 4, 13, null, 'SS택배-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3313', '출고', '')
        ;

insert into groupworks.materialflow_mes (bom_id, defects_num, manufacture_date, mes_id, order_id, quantity, unit_price, item_code, item_name, process_location)
values  (1, 11, '2024-08-16 17:46:40.659212', 1, 1, 100, 500, '0B3760541-I931-1', 'A4', '공장1 A4 제조 공정');

/* ====== Material flow End ======== */
