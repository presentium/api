package ddl;

import ch.presentium.backend.business.model.user.User;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;

public class DDLGenerator {

    @Test
    void generateDDL() {
        var metadata = new MetadataSources(
            new StandardServiceRegistryBuilder()
                .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .applySetting("hibernate.temp.use_jdbc_metadata_defaults", false)
                .build()
        )
            .addAnnotatedClasses(User.class)
            .buildMetadata();

        deleteFile("sql/ddl/create.sql");
        deleteFile("sql/ddl/drop.sql");

        var schemaExport = new SchemaExport();
        schemaExport.setFormat(true);
        schemaExport.setOutputFile("sql/ddl/create.sql");
        schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata);
        schemaExport.setOutputFile("sql/ddl/drop.sql");
        schemaExport.drop(EnumSet.of(TargetType.SCRIPT), metadata);
    }

    private void deleteFile(String path) {
        try {
            Files.delete(Path.of(path));
        } catch (Exception ignored) {}
    }
}
