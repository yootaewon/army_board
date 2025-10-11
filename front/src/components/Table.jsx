import Pagination from "./Pagenation";

export default function Table({
  headers,
  items = [],
  totalPages,
  setCurrentPage,
  currentPage,
  size,
  setSize,
}) {
  const headerKey = headers.map((header) => header.value);

  return (
    <>
      <div className="w-100 d-flex justify-content-start mb-3">
        <select
          onChange={(e) => setSize(e.target.value)}
          className="form-select w-auto"
        >
          <option value="5">5</option>
          <option value="10">10</option>
          <option value="15">15</option>
          <option value="20">20</option>
        </select>
      </div>

      <table className="table table-hover">
        <thead>
          <tr>
            {headers.map((header) => (
              <th key={header.text}>{header.text}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {items.length === 0 ? (
            <tr>
              <td colSpan={headers.length} className="text-center">
                테이블 데이터가 없습니다.
              </td>
            </tr>
          ) : (
            items.map((item, index) => (
              <tr key={index}>
                {headerKey.map((key) => (
                  <td key={key + index}>{item[key]}</td>
                ))}
              </tr>
            ))
          )}
        </tbody>
      </table>

      <Pagination
        setCurrentPage={setCurrentPage}
        currentPage={currentPage}
        totalPages={totalPages}
        size={size}
      />
    </>
  );
}
