CREATE TABLE objetivo (
    id serial PRIMARY KEY,
    nome TEXT NOT NULL,
    prazo_estimado DATE NOT NULL,
    gestor_id uuid NOT NULL,

	FOREIGN KEY(gestor_id) REFERENCES gestor(id)
	ON DELETE CASCADE
);