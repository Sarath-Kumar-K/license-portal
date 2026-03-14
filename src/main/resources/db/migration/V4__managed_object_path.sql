alter table managed_object add column  path varchar(500);
create index idx_managed_object_path on managed_object(path);