SELECT *
FROM `likelion-db`.nation_wide_hospitals
WHERE full_address LIKE '%수원시%'
  and hospital_name LIKE '%피부%';

SELECT *
FROM `likelion-db`.nation_wide_hospitals
WHERE full_address LIKE '%서대문구%'
  and hospital_name LIKE '%보건%';

SELECT *
FROM `likelion-db`.nation_wide_hospitals
WHERE business_type_name in ('보건소', '보건지소', '보진료소')
  and road_name_address LIKE '%서대문구%';
