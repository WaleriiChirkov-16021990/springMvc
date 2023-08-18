package org.chirkov.firstSpringMvcProject.DataAccessObject;

//import javax.swing.tree.RowMapper;
//import javax.swing.tree.TreePath;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
//    /**
//     * Returns the rows that the TreePath instances in <code>path</code>
//     * are being displayed at. The receiver should return an array of
//     * the same length as that passed in, and if one of the TreePaths
//     * in <code>path</code> is not valid its entry in the array should
//     * be set to -1.
//     *
//     * @param path array of TreePath to parse
//     * @return the rows that the TreePath instances in {@code path} are
//     * being displayed at
//     */
//    @Override
//    public int[] getRowsForPaths(TreePath[] path) {
//        return new int[0];
//    }

    @Override
    public Person mapRow(ResultSet resultSet, int index) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setSurname(resultSet.getString("surname"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
}
