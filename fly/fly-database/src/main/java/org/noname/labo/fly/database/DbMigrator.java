package org.noname.labo.fly.database;

import org.flywaydb.core.Flyway;

public class DbMigrator extends Flyway{

	public void migrateDb() {
		repair();
		migrate();
	}
}
