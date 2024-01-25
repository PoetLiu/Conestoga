use liu32;
CREATE TABLE geo_provinces_ca (
id INT(11) NOT NULL DEFAULT '0',
printable_name CHAR(40) COLLATE utf8_unicode_ci NOT NULL,
iso CHAR(2) COLLATE utf8_unicode_ci NOT NULL,
PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO geo_provinces_ca (id,printable_name,iso)
VALUES
(1,'Ontario','ON'),
(2,'Quebec','QC'),
(3,'Nova Scotia','NS'),
(8,'Saskatchewan','SK'),
(9,'Alberta','AB'),
(10,'Newfoundland and Labrador','NL'),
(11,'Northwest Territories','NT'),
(12,'Yukon','YT'),
(13,'Nunavut','NU');
