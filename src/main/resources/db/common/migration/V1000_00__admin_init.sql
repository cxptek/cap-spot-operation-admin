CREATE TABLE trading_pair (
      id BIGINT NOT NULL AUTO_INCREMENT,
      partner_id BIGINT NOT NULL,
      symbol_code VARCHAR(30) NOT NULL,
      active BOOLEAN DEFAULT NULL,
      created_at TIMESTAMP NULL DEFAULT NULL,
      updated_at TIMESTAMP NULL DEFAULT NULL,
      created_by VARCHAR(32) DEFAULT NULL,
      updated_by VARCHAR(32) DEFAULT NULL,
      maker_fee DECIMAL(38, 18) DEFAULT 0,
      taker_fee DECIMAL(38, 18) DEFAULT 0,
      PRIMARY KEY (id)
);

CREATE TABLE server_config (
       id BIGINT AUTO_INCREMENT NOT NULL,
       server_name VARCHAR(64) NOT NULL,
       symbol_code VARCHAR(32) NOT NULL,
       created_at TIMESTAMP NULL DEFAULT NULL,
       updated_at TIMESTAMP NULL DEFAULT NULL,
       created_by VARCHAR(32) DEFAULT NULL,
       updated_by VARCHAR(32) DEFAULT NULL,
       PRIMARY KEY (id)
);

