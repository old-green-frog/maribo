package com.networks.maribo.service;

import com.networks.maribo.LocalContext;

import org.springframework.jdbc.core.RowMapper;
import java.lang.reflect.Field;
import java.util.List;

public abstract class Model {

    protected String oneQuery;
    protected static String allQuery;

//    protected void updateFields() {
//
//        Field[] fields = this.getClass().getFields();
//        List<?> data = LocalContext.runner.query(
//                this.oneQuery, this.MapperClass()
//        );
//
//        Object objClass = data.get(0);
//
//        for (Field field : fields) {
//            try {
//                field.setAccessible(true);
//                field.set(this, field.get(objClass));
//            } catch (IllegalAccessException e) {
//                LocalContext.logger.error(e.getMessage());
//                System.exit(1);
//            }
//        }
//    }

    public static <T extends Model> List<T> getOne(Class<T> cls, RowMapper<T> mapper, int id) {
        return LocalContext.runner.query(
                String.format("SELECT * FROM %s WHERE id=%s", cls.getSimpleName(), id), mapper
        );
    }

    public void updateField(String fieldname, Object value) {
        try {
            Field ff = this.getClass().getDeclaredField(fieldname);
            ff.setAccessible(true);

            if (value.equals(0) && fieldname.equals("order_id")) value = null;

            ff.set(this, value);

            Field ids = this.getClass().getDeclaredField("id");
            ids.setAccessible(true);

//            System.out.println(String.format("UPDATE %s SET %s = '%s' WHERE id = %s",
//                    this.getClass().getSimpleName(),
//                    fieldname, value, ids.get(this)
//            ));

            String query = String.format("UPDATE %s SET %s = '%s' WHERE id = %s",
                    this.getClass().getSimpleName(),
                    fieldname, value, ids.get(this)
            );

            if (value == null) {
                query = String.format("UPDATE %s SET %s = NULL WHERE id = %s",
                        this.getClass().getSimpleName(),
                        fieldname, ids.get(this)
                );
            }

            LocalContext.runner.update(query);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Model> List<T> getAll(Class<T> cls, RowMapper<T> mapper) {
        try {
            return LocalContext.runner.query(
                    (String) cls.getField("allQuery").get(cls), mapper
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save() {
        String createQuery = "INSERT INTO %s(%s) VALUES(%s)";
        String tableName = this.getClass().getSimpleName();

        StringBuilder fieldNames = new StringBuilder();
        StringBuilder values = new StringBuilder();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals("allQuery") || field.getName().equals("products")) continue;
            fieldNames.append(field.getName()).append(", ");
            try {
                if (field.getName().equals("id")) {
                    values.append(field.get(this)).append(", ");
                } else {
                    if (field.get(this) == null) {
                        values.append("NULL, ");
                    } else {
                        values.append("'").append(field.get(this)).append("', ");
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        fieldNames.setLength(fieldNames.length() - 2);
        values.setLength(values.length() - 2);

//        System.out.printf((createQuery) + "%n", tableName, fieldNames, values);

        LocalContext.runner.update(String.format(createQuery, tableName, fieldNames, values));
    }

    public void delete_from_db() {
        String query = "DELETE FROM %s WHERE id=%s";
        String tableName = this.getClass().getSimpleName();
        Field id;
        Object id_integer = 0;
        try {
            id = this.getClass().getDeclaredField("id");
            id.setAccessible(true);
            id_integer = id.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (id_integer.equals(0)) return;

        LocalContext.runner.update(String.format(query, tableName, id_integer));
    }

//    public void kum() {
//        System.out.println("Yo");
//        Field[] fields = this.getClass().getDeclaredFields();
//        System.out.println(Arrays.toString(fields));
//        for (Field field : fields) {
//            field.setAccessible(true);
//            try {
//                System.out.printf("%s -> %s%n", field.getName(), field.get(this));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
