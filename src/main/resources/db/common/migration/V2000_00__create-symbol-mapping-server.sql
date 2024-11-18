CREATE TABLE IF NOT EXISTS public.server_config (
                                      id bigserial NOT NULL,
                                      symbol_code varchar(32) NOT NULL,
                                      "server_name" varchar(64) NOT NULL,
                                      created_at timestamp NOT NULL,
                                      updated_at timestamp NOT NULL,
                                      created_by varchar(128) NULL,
                                      updated_by varchar(128) NULL,
                                      CONSTRAINT server_config_pk PRIMARY KEY (id)
);