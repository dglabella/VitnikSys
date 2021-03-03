CREATE EVENT cierre_diario_de_registros
ON SCHEDULE EVERY 1 MIN STARTS
ON COMPLETION PRESERVE

DO
  UPDATE bdaccesos_test.registros
  SET amount = amount*5
  WHERE DATE = (the the current real time date);



CREATE EVENT cierre_diario_de_registros 
ON SCHEDULE EVERY 1 DAY STARTS (CURRENT_DATE() + 1);
ON COMPLETION PRESERVE
DO 
UPDATE `registros` 
SET `hora_egreso` = ((CURRENT_DATE() - 1) + INTERVAL 23 HOUR + INTERVAL 59 MINUTE + INTERVAL 59 SECOND) 
WHERE `hora_ingreso` IS NOT NULL AND `hora_egreso` IS NULL;


INSERT INTO `bdaccesos_test`.`registros` (`fk_persona`, `fk_acceso`, `hora_ingreso` ,`hora_egreso`, `estado`, `habilitado`) 
VALUES (552, 1, current_date(), (current_date() +1)-1 + interval 23 hour + interval 59 minute + interval 59 second, "ACTIVO", 0);