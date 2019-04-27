package edu.ncsu.whms.operations;


// Imports
import edu.ncsu.whms.ConnectionFactory;

import java.sql.*;


/**
 * Wolf Hospital reporting operations.
 *
 * @author ewhorton.
 */
public class Reports {

    /**
     * SQL string supporting {@link Reports#getMedicalRecordsInRange(int, Date, Date)}.
     */
    private static final String MEDICAL_RECORDS_IN_RANGE = "" +
            "SELECT\n" +
            "    MedicalRecords.RecordType,\n" +
            "    MedicalRecords.Result,\n" +
            "    MedicalRecords.StaffID,\n" +
            "    Date(Visits.StartDate) AS StartDate,\n" +
            "    Date(Visits.EndDate) AS EndDate\n" +
            "FROM Patients\n" +
            "JOIN Visits\n" +
            "ON Patients.PatientID = Visits.PatientID\n" +
            "JOIN (\n" +
            "    (\n" +
            "        SELECT\n" +
            "            Diagnosis AS Result,\n" +
            "            VisitID AS VisitID,\n" +
            "            StaffID AS StaffID,\n" +
            "            'Test' AS RecordType\n" +
            "        FROM Tests\n" +
            "    )\n" +
            "    UNION\n" +
            "    (\n" +
            "        SELECT\n" +
            "            Prescription AS Result,\n" +
            "            VisitID AS VisitID,\n" +
            "            StaffID AS StaffID,\n" +
            "            'Treatment' AS RecordType\n" +
            "        FROM Treatments\n" +
            "    )\n" +
            ") MedicalRecords\n" +
            "ON Visits.VisitID = MedicalRecords.VisitID\n" +
            "WHERE\n" +
            "    Patients.PatientID = ?\n" +
            "    AND ? <= Date(Visits.StartDate)\n" +
            "    AND ? >= Date(Visits.EndDate);";

    /**
     * SQL string supporting {@link Reports#getCurrentBedUsage()}.
     */
    private static final String BED_USAGE = "" +
            "SELECT\n" +
            "    Beds.BedNumber,\n" +
            "    Beds.WardNumber,\n" +
            "    IF(Beds.PatientID IS NOT NULL, 'Yes', 'No') AS BedInUse,\n" +
            "    (\n" +
            "        SELECT COUNT(*)\n" +
            "        FROM Beds AS WardBeds\n" +
            "        WHERE\n" +
            "            WardBeds.WardNumber = Beds.WardNumber\n" +
            "            AND WardBeds.PatientID IS NULL\n" +
            "    ) AS OpenBedsInWard,\n" +
            "    Wards.Capacity AS WardCapacity\n" +
            "FROM Beds\n" +
            "JOIN Wards\n" +
            "ON Wards.WardNumber = Beds.WardNumber;";

    /**
     * SQL string supporting {@link Reports#getNumberOfPatientsPerMonth()}.
     */
    private static final String PATIENTS_PER_MONTH = "" +
            "SELECT\n" +
            "    Year(Visits.StartDate) AS Year,\n" +
            "    Month(Visits.StartDate) AS Month,\n" +
            "    COUNT(*) AS Visits\n" +
            "FROM Visits\n" +
            "GROUP BY Year(Visits.StartDate), Month(Visits.StartDate);";

    /**
     * SQL string supporting {@link Reports#getOccupiedBedsAndCapacity()}.
     */
    private static final String OCCUPIED_BEDS_CAPACITY = "" +
            "SELECT\n" +
            "    Stats.OccupiedBeds,\n" +
            "    Stats.TotalCapacity,\n" +
            "    Stats.OccupiedBeds / Stats.TotalCapacity AS UsagePercentage\n" +
            "FROM (\n" +
            "    SELECT\n" +
            "        (SELECT COUNT(*) FROM Beds WHERE Beds.PatientID IS NOT NULL) \n" +
            "        AS OccupiedBeds,\n" +
            "        (SELECT SUM(Wards.Capacity) FROM Wards) AS TotalCapacity\n" +
            ") Stats;";

    /**
     * SQL string supporting {@link Reports#getPatientsUnderCare(int)}.
     */
    private static final String PATIENTS_UNDER_CARE = "" +
            "SELECT DISTINCT Patients.*\n" +
            "FROM Patients\n" +
            "JOIN Visits\n" +
            "ON Patients.PatientID = Visits.PatientID\n" +
            "JOIN (\n" +
            "    (\n" +
            "        SELECT\n" +
            "            Diagnosis AS Result,\n" +
            "            VisitID AS VisitID,\n" +
            "            StaffID AS StaffID,\n" +
            "            'Test' AS RecordType\n" +
            "        FROM Tests\n" +
            "    )\n" +
            "    UNION\n" +
            "    (\n" +
            "        SELECT\n" +
            "            Prescription AS Result,\n" +
            "            VisitID AS VisitID,\n" +
            "            StaffID AS StaffID,\n" +
            "            'Treatment' AS RecordType\n" +
            "        FROM Treatments\n" +
            "    )\n" +
            ") MedicalRecords\n" +
            "ON Visits.VisitID = MedicalRecords.VisitID\n" +
            "WHERE Visits.EndDate IS NULL and MedicalRecords.StaffID = ?;";

    /**
     * SQL string supporting {@link Reports#getStaffInformationByRole()}.
     */
    private static final String STAFF_INFORMATION_BY_ROLE = "" +
            "SELECT *\n" +
            "FROM StaffMembers\n" +
            "ORDER BY JobTitle;";

    /**
     * Report a patient's medical history (all tests and treatments) for a certain time period. All medical billing
     * for visits that are completely contained within the time period are returned.
     *
     * The result schema is: MedicalRecordsInSpan(RecordType, Result, StaffID, StartDate, EndDate).
     *
     * @param patientID Patient whose medical billing are sought.
     * @param start Start date for time period.
     * @param end End date for time period.
     * @return All medical billing of the patient matched by patientID that occur between start and end.
     * @throws SQLException Thrown if unable to query for medical records.
     */
    public static ResultSet getMedicalRecordsInRange(int patientID, Date start, Date end) throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(MEDICAL_RECORDS_IN_RANGE);
            st.setInt(1, patientID);
            st.setDate(2, start);
            st.setDate(3, end);
            return st.executeQuery();

        }

    }

    /**
     * Report the current usage for all beds.
     *
     * The result schema is: BedUsage(BedNumber, WardNumber, BedInUse, OpenBedsInWard, WardCapacity).
     *
     * @return Usage statistics for all beds.
     * @throws SQLException Thrown if unable to query for bed usage.
     */
    public static ResultSet getCurrentBedUsage() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            return conn.createStatement().executeQuery(BED_USAGE);

        }

    }

    /**
     * Report the number of patient visits per month.
     *
     * The result schema is: VisitStats(Year, Month, Visits).
     *
     * @return Number of visits per month.
     * @throws SQLException Thrown if unable to query for patients visits.
     */
    public static ResultSet getNumberOfPatientsPerMonth() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            return conn.createStatement().executeQuery(PATIENTS_PER_MONTH);

        }

    }

    /**
     * Report the number of occupied beds out of the total hospital capacity.
     *
     * The result schema is: Usage(OccupiedBeds, TotalCapacity, UsagePercentage).
     *
     * @return Hospital usage statistics.
     * @throws SQLException Thrown if unable to query for beds.
     */
    public static ResultSet getOccupiedBedsAndCapacity() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            return conn.createStatement().executeQuery(OCCUPIED_BEDS_CAPACITY);

        }

    }

    /**
     * Report all patients currently checked in who have been treated by a staff member.
     *
     * The result schema is: Patients.
     *
     * @param staffID StaffMember ID.
     *
     * @return Information of patient's cared for by the staff member with `staffID`.
     * @throws SQLException Thrown if unable to query for patients.
     */
    public static ResultSet getPatientsUnderCare(int staffID) throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement st = conn.prepareStatement(PATIENTS_UNDER_CARE);
            st.setInt(1, staffID);
            return st.executeQuery();

        }

    }

    /**
     * Get all staff ordered by role.
     *
     * The result schema is: StaffMembers.
     *
     * @return All staff members by role.
     * @throws SQLException Thrown if unable to query for staff.
     */
    public static ResultSet getStaffInformationByRole() throws SQLException {

        // Get a connection to the database and execute query for medical billing.
        try (Connection conn = ConnectionFactory.getConnection()) {

            return conn.createStatement().executeQuery(STAFF_INFORMATION_BY_ROLE);

        }

    }

}
