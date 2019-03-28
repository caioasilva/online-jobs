INSERT INTO freelancers (id, user_id, name, message, email) VALUES
(1, 3, 'Freelancer Test 1', 'My description', 'free@lancer.com');

--
-- Dumping data for table freelancers_skills
--

INSERT INTO freelancers_skills (freelancer_id, skill) VALUES
(1, 'C++'),
(1, 'Java');

--
-- Dumping data for table jobs
--

INSERT INTO jobs (id, provider_id, title, description, status, payment, creation_date) VALUES
(1, 1, 'Java Developer Needed', 'Description', 'open', '1200.00', '2019-03-24 12:53:25');

--
-- Dumping data for table jobs_keywords
--

INSERT INTO jobs_keywords (job_id, keyword) VALUES
(1, 'developer'),
(1, 'Java'),
(1, 'programming'),
(1, 'web');

--
-- Dumping data for table jobs_offers
--

INSERT INTO jobs_offers (job_id, freelancer_id, status) VALUES
(1, 1, 'waiting');

--
-- Dumping data for table providers
--

INSERT INTO providers (id, user_id, name) VALUES
(1, 2, 'Development Co.');

--
-- Dumping data for table users
--

INSERT INTO users (id, username, password, type) VALUES
(1, 'admin', 'admin', 'a'),
(2, 'provider', 'provider', 'p'),
(3, 'freelancer', 'freelancer', 'f');