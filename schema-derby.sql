--
-- Table structure for table freelancers
--

CREATE TABLE freelancers (
  id integer NOT NULL,
  user_id integer NOT NULL,
  name varchar(200) NOT NULL,
  message clob,
  email varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table freelancers_skills
--

CREATE TABLE freelancers_skills (
  freelancer_id integer NOT NULL,
  skill varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table jobs
--

CREATE TABLE jobs (
  id integer NOT NULL,
  provider_id integer NOT NULL,
  title varchar(200) NOT NULL,
  description clob,
  status varchar(10) NOT NULL DEFAULT 'open',
  payment decimal(10,2) NOT NULL DEFAULT 0.00,
  creation_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table jobs_keywords
--

CREATE TABLE jobs_keywords (
  job_id integer NOT NULL,
  keyword varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table jobs_offers
--

CREATE TABLE jobs_offers (
  job_id integer NOT NULL,
  freelancer_id integer NOT NULL,
  status varchar(10) NOT NULL DEFAULT 'waiting'
);

-- --------------------------------------------------------

--
-- Table structure for table logs
--

CREATE TABLE logs (
  id integer NOT NULL,
  user_id integer NOT NULL,
  description clob NOT NULL,
  job_id integer DEFAULT NULL,
  date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- --------------------------------------------------------

--
-- Table structure for table providers
--

CREATE TABLE providers (
  id integer NOT NULL,
  user_id integer NOT NULL,
  name varchar(200) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table users
--

CREATE TABLE users (
  id integer NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  type char(1) NOT NULL
);

--
-- Indexes for dumped tables
--

--
-- Indexes for table freelancers
--
ALTER TABLE freelancers
  ADD PRIMARY KEY (id);


--
-- Indexes for table freelancers_skills
--
ALTER TABLE freelancers_skills
  ADD PRIMARY KEY (freelancer_id,skill);

--
-- Indexes for table jobs
--
ALTER TABLE jobs
  ADD PRIMARY KEY (id);


--
-- Indexes for table jobs_keywords
--
ALTER TABLE jobs_keywords
  ADD PRIMARY KEY (job_id,keyword);

--
-- Indexes for table jobs_offers
--
ALTER TABLE jobs_offers
  ADD PRIMARY KEY (job_id,freelancer_id);


--
-- Indexes for table logs
--
ALTER TABLE logs
  ADD PRIMARY KEY (id);


--
-- Indexes for table providers
--
ALTER TABLE providers
  ADD PRIMARY KEY (id);

--
-- Indexes for table users
--
ALTER TABLE users
  ADD PRIMARY KEY (id);

--
-- Constraints for dumped tables
--

--
-- Constraints for table freelancers
--
ALTER TABLE freelancers
  ADD CONSTRAINT fk_freelancers_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

--
-- Constraints for table freelancers_skills
--
ALTER TABLE freelancers_skills
  ADD CONSTRAINT fk_freelancers_skills_freelancer_id FOREIGN KEY (freelancer_id) REFERENCES freelancers (id) ON DELETE CASCADE;

--
-- Constraints for table jobs
--
ALTER TABLE jobs
  ADD CONSTRAINT fk_jobs_provider_id FOREIGN KEY (provider_id) REFERENCES providers (id) ON DELETE CASCADE;

--
-- Constraints for table jobs_keywords
--
ALTER TABLE jobs_keywords
  ADD CONSTRAINT fk_jobs_keywords_job_id FOREIGN KEY (job_id) REFERENCES jobs (id) ON DELETE CASCADE;

--
-- Constraints for table jobs_offers
--
ALTER TABLE jobs_offers
  ADD CONSTRAINT fk_jobs_offers_freelancer_id FOREIGN KEY (freelancer_id) REFERENCES freelancers (id) ON DELETE CASCADE;
ALTER TABLE jobs_offers 
  ADD CONSTRAINT fk_jobs_offers_job_id FOREIGN KEY (job_id) REFERENCES jobs (id) ON DELETE CASCADE;

--
-- Constraints for table logs
--
ALTER TABLE logs
  ADD CONSTRAINT fk_logs_job_id FOREIGN KEY (job_id) REFERENCES jobs (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE logs
  ADD CONSTRAINT fk_logs_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table providers
--
ALTER TABLE providers
  ADD CONSTRAINT fk_providers_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;
