INSERT INTO trading_pair (partner_id,symbol_code,active,created_at,updated_at,created_by,updated_by,maker_fee,taker_fee)
VALUES
 (1,'USDT_KDG',true,'2024-06-11 17:15:58.344','2024-06-11 17:15:58.344','Admin','Admin',0.000100000000000000,0.000500000000000000),
 (1,'TON_VIC',true,'2024-06-11 17:15:58.344','2024-06-11 17:15:58.344','Admin','Admin',0.000100000000000000,0.000500000000000000),
 (1,'DOT_VIC',true,'2024-06-11 17:15:58.344','2024-06-11 17:15:58.344','Admin','Admin',0.000100000000000000,0.000500000000000000),
 (1,'SHIB_VIC',true,'2024-06-11 17:15:58.344','2024-06-11 17:15:58.344','Admin','Admin',0.000100000000000000,0.000500000000000000);


INSERT INTO server_config (symbol_code,server_name,created_at,updated_at,created_by,updated_by) VALUES
('USDT_KDG','server_write_side','2024-08-30 10:51:13.976','2024-08-30 10:51:13.976','Admin','Admin'),
('TON_VIC','server_write_side','2024-08-30 10:51:13.976','2024-08-30 10:51:13.976','Admin','Admin'),
('DOT_VIC','server_write_side','2024-08-30 10:51:13.976','2024-08-30 10:51:13.976','Admin','Admin'),
('SHIB_VIC','server_write_side','2024-08-30 10:51:13.976','2024-08-30 10:51:13.976','Admin','Admin');