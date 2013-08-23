drop table if exists creative;

CREATE TABLE `creative` (
  `id` bigint not null auto_increment,
  `screenShotLocation` varchar(128) DEFAULT NULL,
  `sound` tinyint(1) DEFAULT 0,
  `creativeUrl` text DEFAULT NULL,
  `creativeHtml` text DEFAULT NULL,
  `largestSwf` text DEFAULT NULL,
  `largestImg` text DEFAULT NULL,
  `clickThroughUrl` text DEFAULT NULL,
  `landingUrl` text DEFAULT NULL,
  `height` int unsigned DEFAULT 0,
  `width` int unsigned DEFAULT 0,
  `loadTime` int unsigned DEFAULT 0,
  `md5` varchar(128) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  primary key (id),
  index(created)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
