DELETE FROM `saldos` WHERE `nro_camp`= 241;
DELETE FROM `recompras` WHERE `nro_camp` = 241;
DELETE FROM `pagos` WHERE `nro_camp` = 241;
DELETE FROM `observaciones` WHERE `nro_camp` = 241;
DELETE FROM `devoluciones` WHERE `nro_camp` = 241;
DELETE FROM `comisiones` WHERE `nro_camp` = 241;

DELETE `articulos_devueltos`  
FROM `articulos_devueltos` 
INNER JOIN `pedidos` ON `cod_pedido` = `pedidos`.`cod` 
WHERE `pedidos`.`nro_camp` = 241;

DELETE FROM `pedidos` WHERE `nro_camp` = 241;
DELETE FROM `camps` WHERE `nro_camp` = 241;