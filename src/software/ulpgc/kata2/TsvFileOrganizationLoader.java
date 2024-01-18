package software.ulpgc.kata2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TsvFileOrganizationLoader implements OrganizationLoader{
    private final File file;

    public TsvFileOrganizationLoader(File file) {
        this.file = file;
    }

    @Override
    public List<Organization> load() {
        try {
            return load(new FileReader(file));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<Organization> load(FileReader reader) throws IOException {
        return load(new BufferedReader(reader));
    }

    private List<Organization> load(BufferedReader reader) throws IOException {
        return reader.lines().skip(1)
                .map(this::toOrganization)
                .collect(Collectors.toList());
    }

    private Organization toOrganization(String line) {
        return toOrganization(line.split("\t"));
    }

    private Organization toOrganization(String[] fields) {
        return new Organization(
                fields[0],
                fields[1],
                fields[2],
                fields[3],
                fields[4],
                Integer.parseInt(fields[5]),
                fields[6],
                Integer.parseInt(fields[7])
        );
    }
}
