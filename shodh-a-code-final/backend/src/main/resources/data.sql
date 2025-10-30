
INSERT INTO contest (id, name, contest_id) VALUES (1, 'Sample Contest', 'SAMPLE123') ON CONFLICT DO NOTHING;
INSERT INTO problem (id, code, title, statement) VALUES (1, 'PROB1', 'Add A+B', 'Add two numbers') ON CONFLICT DO NOTHING;
INSERT INTO testcase (id, problem_id, stdin, stdout, is_sample) VALUES (1, 1, '2 3', '5', true) ON CONFLICT DO NOTHING;
INSERT INTO testcase (id, problem_id, stdin, stdout, is_sample) VALUES (2, 1, '10 20', '30', false) ON CONFLICT DO NOTHING;
