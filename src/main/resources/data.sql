INSERT INTO users (name, target) VALUES ('User 1', 'NEW');
INSERT INTO users (name, target) VALUES ('User 2', 'PREMIUM');
INSERT INTO users (name, target) VALUES ('User 3', 'PREMIUM');
INSERT INTO users (name, target) VALUES ('User 4', 'FREQUENT');
INSERT INTO users (name, target) VALUES ('User 5', 'FREQUENT');


INSERT INTO target_rules (MIN_AMOUNT, MAX_AMOUNT, MIN_CANT, MAX_CANT, TYPE, RATE, MAX_LOAN) VALUES (0, 100000, 0, 2, 'NEW', 0.15, 500000);
INSERT INTO target_rules (MIN_AMOUNT, MAX_AMOUNT, MIN_CANT, MAX_CANT, TYPE, RATE, MAX_LOAN) VALUES (100000, 500000, 2, 5, 'FREQUENT', 0.10, 1000000);
INSERT INTO target_rules (MIN_AMOUNT, MAX_AMOUNT, MIN_CANT, MAX_CANT, TYPE, RATE, MAX_LOAN) VALUES (500000, 0, 5, 0, 'PREMIUM', 0.05, 5000000);