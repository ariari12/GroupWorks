
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
	 (1,5,'2021-01-01 00:00:00',1,NULL,30000,0,'주소 1','employee1@example.com','사원1','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1100','부장'),
	 (1,4,'2022-01-01 00:00:00',1,NULL,30010,1,'주소 2','employee2@example.com','사원2','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1101','차장'),
	 (1,3,'2023-01-01 00:00:00',1,NULL,30020,2,'주소 3','employee3@example.com','사원3','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1102','과장'),
	 (1,3,'2023-01-01 00:00:00',1,NULL,30030,2,'주소 4','employee4@example.com','사원4','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1103','과장'),
	 (1,2,'2023-06-01 00:00:00',1,NULL,30040,4,'주소 5','employee5@example.com','사원5','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1104','대리'),
	 (1,2,'2023-06-01 00:00:00',1,NULL,30050,4,'주소 6','employee6@example.com','사원6','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1105','대리'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30060,6,'주소 7','employee7@example.com','사원7','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1106','사원'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30070,6,'주소 8','employee8@example.com','사원8','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1107','사원'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30080,6,'주소 9','employee9@example.com','사원9','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1108','사원'),
	 (1,1,'2024-01-01 00:00:00',1,NULL,30090,6,'주소 10','employee10@example.com','사원10','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1109','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',2,NULL,30100,0,'주소 11','employee11@example.com','사원11','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1110','부장'),
	 (1,4,'2022-01-01 00:00:00',2,NULL,30110,11,'주소 12','employee12@example.com','사원12','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1111','차장'),
	 (1,3,'2023-01-01 00:00:00',2,NULL,30120,12,'주소 13','employee13@example.com','사원13','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1112','과장'),
	 (1,3,'2023-01-01 00:00:00',2,NULL,30130,12,'주소 14','employee14@example.com','사원14','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1113','과장'),
	 (1,2,'2023-06-01 00:00:00',2,NULL,30140,14,'주소 15','employee15@example.com','사원15','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1114','대리'),
	 (1,2,'2023-06-01 00:00:00',2,NULL,30150,14,'주소 16','employee16@example.com','사원16','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1115','대리'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30160,16,'주소 17','employee17@example.com','사원17','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1116','사원'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30170,16,'주소 18','employee18@example.com','사원18','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1117','사원'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30180,16,'주소 19','employee19@example.com','사원19','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1118','사원'),
	 (1,1,'2024-01-01 00:00:00',2,NULL,30190,16,'주소 20','employee20@example.com','사원20','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1119','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',3,NULL,30200,0,'주소 21','employee21@example.com','사원21','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1120','부장'),
	 (1,4,'2022-01-01 00:00:00',3,NULL,30210,21,'주소 22','employee22@example.com','사원22','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1121','차장'),
	 (1,3,'2023-01-01 00:00:00',3,NULL,30220,22,'주소 23','employee23@example.com','사원23','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1122','과장'),
	 (1,3,'2023-01-01 00:00:00',3,NULL,30230,22,'주소 24','employee24@example.com','사원24','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1123','과장'),
	 (1,2,'2023-06-01 00:00:00',3,NULL,30240,24,'주소 25','employee25@example.com','사원25','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1124','대리'),
	 (1,2,'2023-06-01 00:00:00',3,NULL,30250,24,'주소 26','employee26@example.com','사원26','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1125','대리'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30260,26,'주소 27','employee27@example.com','사원27','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1126','사원'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30270,26,'주소 28','employee28@example.com','사원28','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1127','사원'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30280,26,'주소 29','employee29@example.com','사원29','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1128','사원'),
	 (1,1,'2024-01-01 00:00:00',3,NULL,30290,26,'주소 30','employee30@example.com','사원30','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1129','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',4,NULL,30300,0,'주소 31','employee31@example.com','사원31','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1130','부장'),
	 (1,4,'2022-01-01 00:00:00',4,NULL,30310,31,'주소 32','employee32@example.com','사원32','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1131','차장'),
	 (1,3,'2023-01-01 00:00:00',4,NULL,30320,32,'주소 33','employee33@example.com','사원33','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1132','과장'),
	 (1,3,'2023-01-01 00:00:00',4,NULL,30330,32,'주소 34','employee34@example.com','사원34','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1133','과장'),
	 (1,2,'2023-06-01 00:00:00',4,NULL,30340,34,'주소 35','employee35@example.com','사원35','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1134','대리'),
	 (1,2,'2023-06-01 00:00:00',4,NULL,30350,34,'주소 36','employee36@example.com','사원36','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1135','대리'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30360,36,'주소 37','employee37@example.com','사원37','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1136','사원'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30370,36,'주소 38','employee38@example.com','사원38','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1137','사원'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30380,36,'주소 39','employee39@example.com','사원39','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1138','사원'),
	 (1,1,'2024-01-01 00:00:00',4,NULL,30390,36,'주소 40','employee40@example.com','사원40','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1139','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',5,NULL,30400,0,'주소 41','employee41@example.com','사원41','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1140','부장'),
	 (1,4,'2022-01-01 00:00:00',5,NULL,30410,41,'주소 42','employee42@example.com','사원42','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1141','차장'),
	 (1,3,'2023-01-01 00:00:00',5,NULL,30420,42,'주소 43','employee43@example.com','사원43','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1142','과장'),
	 (1,3,'2023-01-01 00:00:00',5,NULL,30430,42,'주소 44','employee44@example.com','사원44','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1143','과장'),
	 (1,2,'2023-06-01 00:00:00',5,NULL,30440,44,'주소 45','employee45@example.com','사원45','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1144','대리'),
	 (1,2,'2023-06-01 00:00:00',5,NULL,30450,44,'주소 46','employee46@example.com','사원46','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1145','대리'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30460,46,'주소 47','employee47@example.com','사원47','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1146','사원'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30470,46,'주소 48','employee48@example.com','사원48','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1147','사원'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30480,46,'주소 49','employee49@example.com','사원49','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1148','사원'),
	 (1,1,'2024-01-01 00:00:00',5,NULL,30490,46,'주소 50','employee50@example.com','사원50','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1149','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',6,NULL,30500,0,'주소 51','employee51@example.com','사원51','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1150','부장'),
	 (1,4,'2022-01-01 00:00:00',6,NULL,30510,51,'주소 52','employee52@example.com','사원52','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1151','차장'),
	 (1,3,'2023-01-01 00:00:00',6,NULL,30520,52,'주소 53','employee53@example.com','사원53','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1152','과장'),
	 (1,3,'2023-01-01 00:00:00',6,NULL,30530,52,'주소 54','employee54@example.com','사원54','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1153','과장'),
	 (1,2,'2023-06-01 00:00:00',6,NULL,30540,54,'주소 55','employee55@example.com','사원55','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1154','대리'),
	 (1,2,'2023-06-01 00:00:00',6,NULL,30550,54,'주소 56','employee56@example.com','사원56','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1155','대리'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30560,56,'주소 57','employee57@example.com','사원57','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1156','사원'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30570,56,'주소 58','employee58@example.com','사원58','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1157','사원'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30580,56,'주소 59','employee59@example.com','사원59','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1158','사원'),
	 (1,1,'2024-01-01 00:00:00',6,NULL,30590,56,'주소 60','employee60@example.com','사원60','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1159','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',7,NULL,30600,0,'주소 61','employee61@example.com','사원61','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1160','부장'),
	 (1,4,'2022-01-01 00:00:00',7,NULL,30610,61,'주소 62','employee62@example.com','사원62','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1161','차장'),
	 (1,3,'2023-01-01 00:00:00',7,NULL,30620,62,'주소 63','employee63@example.com','사원63','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1162','과장'),
	 (1,3,'2023-01-01 00:00:00',7,NULL,30630,62,'주소 64','employee64@example.com','사원64','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1163','과장'),
	 (1,2,'2023-06-01 00:00:00',7,NULL,30640,64,'주소 65','employee65@example.com','사원65','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1164','대리'),
	 (1,2,'2023-06-01 00:00:00',7,NULL,30650,64,'주소 66','employee66@example.com','사원66','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1165','대리'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30660,66,'주소 67','employee67@example.com','사원67','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1166','사원'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30670,66,'주소 68','employee68@example.com','사원68','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1167','사원'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30680,66,'주소 69','employee69@example.com','사원69','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1168','사원'),
	 (1,1,'2024-01-01 00:00:00',7,NULL,30690,66,'주소 70','employee70@example.com','사원70','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1169','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',8,NULL,30700,0,'주소 71','employee71@example.com','사원71','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1170','부장'),
	 (1,4,'2022-01-01 00:00:00',8,NULL,30710,71,'주소 72','employee72@example.com','사원72','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1171','차장'),
	 (1,3,'2023-01-01 00:00:00',8,NULL,30720,72,'주소 73','employee73@example.com','사원73','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1172','과장'),
	 (1,3,'2023-01-01 00:00:00',8,NULL,30730,72,'주소 74','employee74@example.com','사원74','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1173','과장'),
	 (1,2,'2023-06-01 00:00:00',8,NULL,30740,74,'주소 75','employee75@example.com','사원75','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1174','대리'),
	 (1,2,'2023-06-01 00:00:00',8,NULL,30750,74,'주소 76','employee76@example.com','사원76','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1175','대리'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30760,76,'주소 77','employee77@example.com','사원77','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1176','사원'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30770,76,'주소 78','employee78@example.com','사원78','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1177','사원'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30780,76,'주소 79','employee79@example.com','사원79','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1178','사원'),
	 (1,1,'2024-01-01 00:00:00',8,NULL,30790,76,'주소 80','employee80@example.com','사원80','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1179','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',9,NULL,30800,0,'주소 81','employee81@example.com','사원81','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1180','부장'),
	 (1,4,'2022-01-01 00:00:00',9,NULL,30810,81,'주소 82','employee82@example.com','사원82','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1181','차장'),
	 (1,3,'2023-01-01 00:00:00',9,NULL,30820,82,'주소 83','employee83@example.com','사원83','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1182','과장'),
	 (1,3,'2023-01-01 00:00:00',9,NULL,30830,82,'주소 84','employee84@example.com','사원84','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1183','과장'),
	 (1,2,'2023-06-01 00:00:00',9,NULL,30840,84,'주소 85','employee85@example.com','사원85','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1184','대리'),
	 (1,2,'2023-06-01 00:00:00',9,NULL,30850,84,'주소 86','employee86@example.com','사원86','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1185','대리'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30860,86,'주소 87','employee87@example.com','사원87','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1186','사원'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30870,86,'주소 88','employee88@example.com','사원88','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1187','사원'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30880,86,'주소 89','employee89@example.com','사원89','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1188','사원'),
	 (1,1,'2024-01-01 00:00:00',9,NULL,30890,86,'주소 90','employee90@example.com','사원90','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1189','사원');
INSERT INTO groupworks.employee (is_active,rank_id,created_date,department_id,last_modified_date,salary,supervisor_id,address,email,employee_name,employee_pw,gender,phone_number,rank_name) VALUES
	 (1,5,'2021-01-01 00:00:00',10,NULL,30900,0,'주소 91','employee91@example.com','사원91','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1190','부장'),
	 (1,4,'2022-01-01 00:00:00',10,NULL,30910,91,'주소 92','employee92@example.com','사원92','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1191','차장'),
	 (1,3,'2023-01-01 00:00:00',10,NULL,30920,92,'주소 93','employee93@example.com','사원93','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1192','과장'),
	 (1,3,'2023-01-01 00:00:00',10,NULL,30930,92,'주소 94','employee94@example.com','사원94','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1193','과장'),
	 (1,2,'2023-06-01 00:00:00',10,NULL,30940,94,'주소 95','employee95@example.com','사원95','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1194','대리'),
	 (1,2,'2023-06-01 00:00:00',10,NULL,30950,94,'주소 96','employee96@example.com','사원96','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1195','대리'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30960,96,'주소 97','employee97@example.com','사원97','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1196','사원'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30970,96,'주소 98','employee98@example.com','사원98','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1197','사원'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30980,96,'주소 99','employee99@example.com','사원99','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','여','010-1111-1198','사원'),
	 (1,1,'2024-01-01 00:00:00',10,NULL,30990,96,'주소 100','employee100@example.com','사원100','$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO','남','010-1111-1199','사원');


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




/* Material flow Start */
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 123번지 45층', '중앙HTA', '123-45-67900', '최기리', '010-5678-1234', '02-1234-5678', 'IT 교육, 국비훈련', '교육 서비스업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 이천시 이천동 2000번지 2000-1', '◎◎제약™', '123-45-67890', '홍길동', '010-1234-1325', '031-123-1325', '유제품', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 123', '◇◇식품', '124-45-67891', '김영희', '010-5678-1325', '02-567-1325', '음료수', '식품업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('부산광역시 해운대구 우동 1234', '☆☆유통', '125-45-67892', '이철수', '010-8765-4321', '051-876-4321', '가전제품', '유통업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('대구광역시 달서구 상인동 987', '▲▲무역', '126-45-67893', '박영수', '010-4567-7890', '053-456-7890', '자동차 부품', '무역업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('울산광역시 남구 삼산동 456', '♤♤화학', '127-45-67894', '최지우', '010-2345-6789', '052-234-6789', '화학물질', '화학업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('전라북도 전주시 완산구 서노송동 789', '◆◆농산', '128-45-67895', '김민수', '010-3456-7891', '063-345-7891', '농산물', '농업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경상남도 창원시 마산합포구 중앙동 123', '◇◇기계', '129-45-67896', '이성민', '010-4567-8912', '055-456-8912', '기계부품', '기계업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 수원시 팔달구 매산로 456', '○○전자', '130-45-67897', '박현준', '010-5678-9123', '031-567-9123', '전자제품', '전자업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 마포구 서교동 789', '△△의료', '131-45-67898', '최예진', '010-6789-1234', '02-678-1234', '의료기기', '의료업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 123번지 45층', '중앙HTA', '123-45-67900', '최기리', '010-5678-1234', '02-1234-5678', 'IT 교육, 국비훈련', '교육 서비스업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('대전광역시 중구 은행동 123', '▣▣섬유', '132-45-67899', '홍석현', '010-7891-2345', '042-789-2345', '섬유제품', '섬유업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 서초동 123-45', '삼성전자', '123-45-67890', '이재용', '010-1234-5678', '02-123-4567', '전자제품', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 영등포구 여의도동 234-56', 'LG화학', '124-45-67891', '신학철', '010-2345-6789', '02-234-5678', '화학물질', '화학업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 서초구 양재동 345-67', '현대자동차', '125-45-67892', '정의선', '010-3456-7890', '02-345-6789', '자동차', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 성남시 분당구 불정로 6', '네이버', '126-45-67893', '최수연', '010-4567-8901', '031-456-7890', '인터넷 서비스', '서비스업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 성남시 분당구 대왕판교로 645번길 16', '카카오', '127-45-67894', '홍은택', '010-5678-9012', '031-567-8901', '모바일 서비스', '정보통신업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 용산구 한강대로 100', '아모레퍼시픽', '128-45-67895', '서경배', '010-6789-0123', '02-678-9012', '화장품', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강남구 테헤란로 152', '하이트진로', '129-45-67896', '김인규', '010-7890-1234', '02-789-0123', '음료', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('경기도 이천시 부발읍 경충대로 2091', 'SK하이닉스', '130-45-67897', '이석희', '010-8901-2345', '031-890-1234', '반도체', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 중구 동호로 330', 'CJ제일제당', '131-45-67898', '강신호', '010-9012-3456', '02-901-2345', '식품', '제조업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('서울특별시 강서구 하늘길 77', '대한항공', '132-45-67899', '우기홍', '010-0123-4567', '02-012-3456', '항공 서비스', '운송업');
INSERT INTO groupworks.materialflow_business (address, business_name, business_number, ceo, ceo_tel, fax, item, type) VALUES ('test test', '페이퍼컴퍼니', '000-00-00001', '나대표', '010-0000-0000', '00-000-000', '종이', '제조업');

INSERT INTO groupworks.material_business_manager (business_id, email, name, phone) VALUES (23, 'manager01@test.com', '담당자1', '010-0000-0000');
INSERT INTO groupworks.material_business_manager (business_id, email, name, phone) VALUES (23, 'manager02@test.com', '담당자2', '010-0000-0001');
INSERT INTO groupworks.material_business_manager (business_id, email, name, phone) VALUES (23, 'manager03@test.com', '담당자3', '010-0000-0003');
INSERT INTO groupworks.material_business_manager (business_id, email, name, phone) VALUES (23, 'manager04@test.com', '담당자4', '010-0000-0004');
INSERT INTO groupworks.material_business_manager (business_id, email, name, phone) VALUES (23, 'manager05@test.com', '담당자5', '010-0000-0005');

INSERT INTO groupworks.materialflow_order (delivery_date, due_date, order_date, zip_code, employee_id, manager_id, tex_amount, total_amount, address, address_detail, classification, order_code) VALUES ('2024-08-14', '2024-08-29', '2024-08-07', '00112', 1, 2, 10, 2000, 'test', 'test', '수주', '2024081412-0B3760541-I931');
INSERT INTO groupworks.materialflow_order (delivery_date, due_date, order_date, zip_code, employee_id, manager_id, tex_amount, total_amount, address, address_detail, classification, order_code) VALUES ('2024-08-14', '2024-08-22', '2024-08-14', '00002', 1, 3, 10, 17500, 'test', 'test', '발주', '2024081414-0A6653603-X773');

INSERT INTO groupworks.materialflow_bom (order_id, quantity, unit_price, item_code, item_name, status) VALUES (1, 2, 1000, '0B3760541-I931-1', 'A4-200', true);
INSERT INTO groupworks.materialflow_bom (order_id, quantity, unit_price, item_code, item_name, status) VALUES (2, 5, 980, '0A6653603-X773-1', 'A4-300', false);
INSERT INTO groupworks.materialflow_bom (order_id, quantity, unit_price, item_code, item_name, status) VALUES (2, 3, 1200, '0A6653603-X773-2', 'A3-200', false);
INSERT INTO groupworks.materialflow_bom (order_id, quantity, unit_price, item_code, item_name, status) VALUES (2, 3, 3000, '0A6653603-X773-3', 'B3-300', true);

INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, '2024-08-15', 1, null, '', 'test-a', '', '0B3760541-I931-115602', '입고', 'test-b');
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, '2024-08-15', 1, null, '', 'test-a', '', '0B3760541-I931-125603', '입고', 'test-b');
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 2, null, null, null, null, '0A6653603-X773-113', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 2, null, null, null, null, '0A6653603-X773-124', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 2, null, null, null, null, '0A6653603-X773-135', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 2, null, null, null, null, '0A6653603-X773-146', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 2, null, null, null, null, '0A6653603-X773-157', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 3, null, null, null, null, '0A6653603-X773-218', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 3, null, null, null, null, '0A6653603-X773-229', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES (null, null, 3, null, null, null, null, '0A6653603-X773-2310', null, null);
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES ('2024-08-14', null, 4, null, 'SS택배-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3111', '출고', '');
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES ('2024-08-14', null, 4, null, 'SS택배-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3212', '출고', '');
INSERT INTO groupworks.materialflow_item (delivery_time, storage_time, bom_id, mes_id, delivery_manager, storage_manager, delivery_location, item_code, item_status, storage_location) VALUES ('2024-08-14', null, 4, null, 'SS택배-ㅁㅁㅁ', '', 'B번 9출하장', '0A6653603-X773-3313', '출고', '');

INSERT INTO groupworks.materialflow_mes (bom_id, defects_num, manufacture_date, order_id, quantity, unit_price, item_code, item_name, process_location) VALUES (1, 11, '2024-08-16 17:46:40.659212', 1, 100, 500, '0B3760541-I931-1', 'A4', '공장1 A4 제조 공정');

/* Material flow End */


-- 1~10
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-01 10:00:00', 5, '2025-03-01 10:00:00', 'Vacation', '휴가 1', '2025-09-01', '2025-03-02', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-02 10:00:00', 7, '2025-03-02 10:00:00', 'Vacation', '휴가 2', '2025-09-04', '2025-03-03', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-03 10:00:00', 1, '2025-03-03 10:00:00', 'Vacation', '휴가 3', '2025-09-07', '2025-03-04', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-04 10:00:00', 3, '2025-03-04 10:00:00', 'Vacation', '휴가 4', '2025-09-10', '2025-03-05', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-05 10:00:00', 9, '2025-03-05 10:00:00', 'Vacation', '휴가 5', '2025-09-13', '2025-03-06', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-06 10:00:00', 4, '2025-03-06 10:00:00', 'Vacation', '휴가 6', '2025-09-16', '2025-03-07', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-07 10:00:00', 6, '2025-03-07 10:00:00', 'Vacation', '휴가 7', '2025-09-19', '2025-03-08', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-08 10:00:00', 10, '2025-03-08 10:00:00', 'Vacation', '휴가 8', '2025-09-22', '2025-03-09', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-09 10:00:00', 8, '2025-03-09 10:00:00', 'Vacation', '휴가 9', '2025-09-25', '2025-03-10', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-10 10:00:00', 2, '2025-03-10 10:00:00', 'Vacation', '휴가 10', '2025-09-28', '2025-03-11', '💼 기타');

-- 11~20
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-11 10:00:00', 5, '2025-03-11 10:00:00', 'Vacation', '휴가 11', '2025-09-30', '2025-03-12', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-12 10:00:00', 7, '2025-03-12 10:00:00', 'Vacation', '휴가 12', '2025-10-03', '2025-03-13', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-13 10:00:00', 1, '2025-03-13 10:00:00', 'Vacation', '휴가 13', '2025-10-06', '2025-03-14', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-14 10:00:00', 3, '2025-03-14 10:00:00', 'Vacation', '휴가 14', '2025-10-09', '2025-03-15', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-15 10:00:00', 9, '2025-03-15 10:00:00', 'Vacation', '휴가 15', '2025-10-12', '2025-03-16', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-16 10:00:00', 4, '2025-03-16 10:00:00', 'Vacation', '휴가 16', '2025-10-15', '2025-03-17', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-17 10:00:00', 6, '2025-03-17 10:00:00', 'Vacation', '휴가 17', '2025-10-18', '2025-03-18', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-18 10:00:00', 10, '2025-03-18 10:00:00', 'Vacation', '휴가 18', '2025-10-21', '2025-03-19', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-19 10:00:00', 8, '2025-03-19 10:00:00', 'Vacation', '휴가 19', '2025-10-24', '2025-03-20', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-20 10:00:00', 2, '2025-03-20 10:00:00', 'Vacation', '휴가 20', '2025-10-27', '2025-03-21', '🕒 반차');

-- 21~30
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-21 10:00:00', 5, '2025-03-21 10:00:00', 'Vacation', '휴가 21', '2025-10-30', '2025-03-22', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-22 10:00:00', 7, '2025-03-22 10:00:00', 'Vacation', '휴가 22', '2025-11-02', '2025-03-23', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-23 10:00:00', 1, '2025-03-23 10:00:00', 'Vacation', '휴가 23', '2025-11-05', '2025-03-24', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-24 10:00:00', 3, '2025-03-24 10:00:00', 'Vacation', '휴가 24', '2025-11-08', '2025-03-25', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-25 10:00:00', 9, '2025-03-25 10:00:00', 'Vacation', '휴가 25', '2025-11-11', '2025-03-26', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-26 10:00:00', 4, '2025-03-26 10:00:00', 'Vacation', '휴가 26', '2025-11-14', '2025-03-27', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-27 10:00:00', 6, '2025-03-27 10:00:00', 'Vacation', '휴가 27', '2025-11-17', '2025-03-28', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-28 10:00:00', 10, '2025-03-28 10:00:00', 'Vacation', '휴가 28', '2025-11-20', '2025-03-29', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-29 10:00:00', 8, '2025-03-29 10:00:00', 'Vacation', '휴가 29', '2025-11-23', '2025-03-30', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-30 10:00:00', 2, '2025-03-30 10:00:00', 'Vacation', '휴가 30', '2025-11-26', '2025-03-31', '💼 기타');

-- 31~40
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-03-31 10:00:00', 5, '2025-03-31 10:00:00', 'Vacation', '휴가 31', '2025-11-29', '2025-04-01', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-01 10:00:00', 7, '2025-04-01 10:00:00', 'Vacation', '휴가 32', '2025-12-02', '2025-04-02', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-02 10:00:00', 1, '2025-04-02 10:00:00', 'Vacation', '휴가 33', '2025-12-05', '2025-04-03', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-03 10:00:00', 3, '2025-04-03 10:00:00', 'Vacation', '휴가 34', '2025-12-08', '2025-04-04', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-04 10:00:00', 9, '2025-04-04 10:00:00', 'Vacation', '휴가 35', '2025-12-11', '2025-04-05', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-05 10:00:00', 4, '2025-04-05 10:00:00', 'Vacation', '휴가 36', '2025-12-14', '2025-04-06', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-06 10:00:00', 6, '2025-04-06 10:00:00', 'Vacation', '휴가 37', '2025-12-17', '2025-04-07', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-07 10:00:00', 10, '2025-04-07 10:00:00', 'Vacation', '휴가 38', '2025-12-20', '2025-04-08', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-08 10:00:00', 8, '2025-04-08 10:00:00', 'Vacation', '휴가 39', '2025-12-23', '2025-04-09', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-09 10:00:00', 2, '2025-04-09 10:00:00', 'Vacation', '휴가 40', '2025-12-26', '2025-04-10', '🕒 반차');

-- 41~50
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-10 10:00:00', 5, '2025-04-10 10:00:00', 'Vacation', '휴가 41', '2025-12-29', '2025-04-11', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-11 10:00:00', 7, '2025-04-11 10:00:00', 'Vacation', '휴가 42', '2026-01-01', '2025-04-12', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-12 10:00:00', 1, '2025-04-12 10:00:00', 'Vacation', '휴가 43', '2026-01-04', '2025-04-13', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-13 10:00:00', 3, '2025-04-13 10:00:00', 'Vacation', '휴가 44', '2026-01-07', '2025-04-14', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-14 10:00:00', 9, '2025-04-14 10:00:00', 'Vacation', '휴가 45', '2026-01-10', '2025-04-15', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-15 10:00:00', 4, '2025-04-15 10:00:00', 'Vacation', '휴가 46', '2026-01-13', '2025-04-16', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-16 10:00:00', 6, '2025-04-16 10:00:00', 'Vacation', '휴가 47', '2026-01-16', '2025-04-17', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-17 10:00:00', 10, '2025-04-17 10:00:00', 'Vacation', '휴가 48', '2026-01-19', '2025-04-18', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-18 10:00:00', 8, '2025-04-18 10:00:00', 'Vacation', '휴가 49', '2026-01-22', '2025-04-19', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-19 10:00:00', 2, '2025-04-19 10:00:00', 'Vacation', '휴가 50', '2026-01-25', '2025-04-20', '💼 기타');

-- 51~60
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-20 10:00:00', 5, '2025-04-20 10:00:00', 'Vacation', '휴가 51', '2026-01-28', '2025-04-21', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-21 10:00:00', 7, '2025-04-21 10:00:00', 'Vacation', '휴가 52', '2026-01-31', '2025-04-22', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-22 10:00:00', 1, '2025-04-22 10:00:00', 'Vacation', '휴가 53', '2026-02-03', '2025-04-23', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-23 10:00:00', 3, '2025-04-23 10:00:00', 'Vacation', '휴가 54', '2026-02-06', '2025-04-24', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-24 10:00:00', 9, '2025-04-24 10:00:00', 'Vacation', '휴가 55', '2026-02-09', '2025-04-25', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-25 10:00:00', 4, '2025-04-25 10:00:00', 'Vacation', '휴가 56', '2026-02-12', '2025-04-26', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-26 10:00:00', 6, '2025-04-26 10:00:00', 'Vacation', '휴가 57', '2026-02-15', '2025-04-27', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-27 10:00:00', 10, '2025-04-27 10:00:00', 'Vacation', '휴가 58', '2026-02-18', '2025-04-28', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-28 10:00:00', 8, '2025-04-28 10:00:00', 'Vacation', '휴가 59', '2026-02-21', '2025-04-29', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-29 10:00:00', 2, '2025-04-29 10:00:00', 'Vacation', '휴가 60', '2026-02-24', '2025-04-30', '🕒 반차');

-- 61~70
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-04-30 10:00:00', 5, '2025-04-30 10:00:00', 'Vacation', '휴가 61', '2026-02-27', '2025-05-01', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-01 10:00:00', 7, '2025-05-01 10:00:00', 'Vacation', '휴가 62', '2026-03-01', '2025-05-02', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-02 10:00:00', 1, '2025-05-02 10:00:00', 'Vacation', '휴가 63', '2026-03-04', '2025-05-03', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-03 10:00:00', 3, '2025-05-03 10:00:00', 'Vacation', '휴가 64', '2026-03-07', '2025-05-04', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-04 10:00:00', 9, '2025-05-04 10:00:00', 'Vacation', '휴가 65', '2026-03-10', '2025-05-05', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-05 10:00:00', 4, '2025-05-05 10:00:00', 'Vacation', '휴가 66', '2026-03-13', '2025-05-06', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-06 10:00:00', 6, '2025-05-06 10:00:00', 'Vacation', '휴가 67', '2026-03-16', '2025-05-07', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-07 10:00:00', 10, '2025-05-07 10:00:00', 'Vacation', '휴가 68', '2026-03-19', '2025-05-08', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-08 10:00:00', 8, '2025-05-08 10:00:00', 'Vacation', '휴가 69', '2026-03-22', '2025-05-09', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-09 10:00:00', 2, '2025-05-09 10:00:00', 'Vacation', '휴가 70', '2026-03-25', '2025-05-10', '💼 기타');

-- 71~80
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-10 10:00:00', 5, '2025-05-10 10:00:00', 'Vacation', '휴가 71', '2026-03-28', '2025-05-11', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-11 10:00:00', 7, '2025-05-11 10:00:00', 'Vacation', '휴가 72', '2026-04-01', '2025-05-12', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-12 10:00:00', 1, '2025-05-12 10:00:00', 'Vacation', '휴가 73', '2026-04-04', '2025-05-13', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-13 10:00:00', 3, '2025-05-13 10:00:00', 'Vacation', '휴가 74', '2026-04-07', '2025-05-14', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-14 10:00:00', 9, '2025-05-14 10:00:00', 'Vacation', '휴가 75', '2026-04-10', '2025-05-15', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-15 10:00:00', 4, '2025-05-15 10:00:00', 'Vacation', '휴가 76', '2026-04-13', '2025-05-16', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-16 10:00:00', 6, '2025-05-16 10:00:00', 'Vacation', '휴가 77', '2026-04-16', '2025-05-17', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-17 10:00:00', 10, '2025-05-17 10:00:00', 'Vacation', '휴가 78', '2026-04-19', '2025-05-18', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-18 10:00:00', 8, '2025-05-18 10:00:00', 'Vacation', '휴가 79', '2026-04-22', '2025-05-19', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-19 10:00:00', 2, '2025-05-19 10:00:00', 'Vacation', '휴가 80', '2026-04-25', '2025-05-20', '🕒 반차');

-- 81~90
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-20 10:00:00', 5, '2025-05-20 10:00:00', 'Vacation', '휴가 81', '2026-04-28', '2025-05-21', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-21 10:00:00', 7, '2025-05-21 10:00:00', 'Vacation', '휴가 82', '2026-05-01', '2025-05-22', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-22 10:00:00', 1, '2025-05-22 10:00:00', 'Vacation', '휴가 83', '2026-05-04', '2025-05-23', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-23 10:00:00', 3, '2025-05-23 10:00:00', 'Vacation', '휴가 84', '2026-05-07', '2025-05-24', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-24 10:00:00', 9, '2025-05-24 10:00:00', 'Vacation', '휴가 85', '2026-05-10', '2025-05-25', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-25 10:00:00', 4, '2025-05-25 10:00:00', 'Vacation', '휴가 86', '2026-05-13', '2025-05-26', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-26 10:00:00', 6, '2025-05-26 10:00:00', 'Vacation', '휴가 87', '2026-05-16', '2025-05-27', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-27 10:00:00', 10, '2025-05-27 10:00:00', 'Vacation', '휴가 88', '2026-05-19', '2025-05-28', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-28 10:00:00', 8, '2025-05-28 10:00:00', 'Vacation', '휴가 89', '2026-05-22', '2025-05-29', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-29 10:00:00', 2, '2025-05-29 10:00:00', 'Vacation', '휴가 90', '2026-05-25', '2025-05-30', '💼 기타');

-- 91~100
INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-30 10:00:00', 5, '2025-05-30 10:00:00', 'Vacation', '휴가 91', '2026-05-28', '2025-05-31', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-05-31 10:00:00', 7, '2025-05-31 10:00:00', 'Vacation', '휴가 92', '2026-06-01', '2025-06-01', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-01 10:00:00', 1, '2025-06-01 10:00:00', 'Vacation', '휴가 93', '2026-06-04', '2025-06-02', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-02 10:00:00', 3, '2025-06-02 10:00:00', 'Vacation', '휴가 94', '2026-06-07', '2025-06-03', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-03 10:00:00', 9, '2025-06-03 10:00:00', 'Vacation', '휴가 95', '2026-06-10', '2025-06-04', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-04 10:00:00', 4, '2025-06-04 10:00:00', 'Vacation', '휴가 96', '2026-06-13', '2025-06-05', '🕒 반차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-05 10:00:00', 6, '2025-06-05 10:00:00', 'Vacation', '휴가 97', '2026-06-16', '2025-06-06', '💊 병가');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-06 10:00:00', 10, '2025-06-06 10:00:00', 'Vacation', '휴가 98', '2026-06-19', '2025-06-07', '💼 기타');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-07 10:00:00', 8, '2025-06-07 10:00:00', 'Vacation', '휴가 99', '2026-06-22', '2025-06-08', '🌴 연차');

INSERT INTO groupworks.calendar (created_date, employee_id, last_modified_date, dtype, contents, end_date, start_date, title)
VALUES ('2025-06-08 10:00:00', 2, '2025-06-08 10:00:00', 'Vacation', '휴가 100', '2026-06-25', '2025-06-09', '🕒 반차');





INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 1, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 2, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 3, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 4, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 5, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 6, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 7, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 8, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 9, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 10, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 11, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 12, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 13, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 14, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 15, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 16, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 17, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 18, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 19, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 20, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 21, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 22, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 23, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 24, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 25, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 26, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 27, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 28, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 29, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 30, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 31, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 32, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 33, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 34, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 35, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 36, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 37, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 38, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 39, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 40, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 41, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 42, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 43, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 44, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 45, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 46, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 47, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 48, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 49, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 50, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 51, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 52, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 53, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 54, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 55, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 56, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 57, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 58, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 59, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 60, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 61, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 62, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 63, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 64, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 65, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 66, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 67, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 68, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 69, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 70, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 71, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 72, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 73, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 74, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 75, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 76, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 77, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 78, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 79, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 80, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 81, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 82, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 83, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 84, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 85, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 86, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 87, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 88, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 89, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 90, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 91, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 92, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 93, NULL, NULL, 'AM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 94, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 95, NULL, NULL, NULL, 'PENDING', 'OTHER');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 96, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (0.5, 97, NULL, NULL, 'PM', 'PENDING', 'HALF');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (2, 98, NULL, NULL, NULL, 'PENDING', 'ANNUAL');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (1, 99, NULL, NULL, NULL, 'PENDING', 'SICK');
INSERT INTO groupworks.vacation (used_vacation, calendar_id, approver_id, approver_name, am_pm, status, vacation_type) VALUES (3, 100, NULL, NULL, NULL, 'PENDING', 'OTHER');

