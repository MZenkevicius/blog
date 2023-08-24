insert into topic (title, header) values
('Most popular films', 'Discussions about pupular films'),
('Top rated films', 'Discussions about top rated films'),
('Latest movies', 'Discussions about latest movies'),
('Most expensive movies', 'Discussions about most expensive movies');

insert into comment (text, topic_id) values
('It think something something about Most popular films', 1),
('But I think something otherwise about Most popular films', 1),
('Nononono about Most popular films', 1),

('It think something something about top rated films', 2),
('But I think something otherwise about top rated films', 2),

('It think something something about latest movies', 3);