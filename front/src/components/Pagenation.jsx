const Pagination = ({ totalPages, setCurrentPage, currentPage, size }) => {
  const onFirstPage = () => setCurrentPage(1);

  const onLastPage = () => setCurrentPage(totalPages);

  const onNextPage = () => {
    if (currentPage < totalPages) setCurrentPage(currentPage + 1);
  };

  const onPrevPage = () => {
    if (currentPage > 1) setCurrentPage(currentPage - 1);
  };

  const renderPageNumbers = () => {
    const pages = [];
    let start = Math.max(1, currentPage - Math.floor(size / 2));
    let end = Math.min(totalPages, start + size - 1);

    if (end - start < size - 1) {
      start = Math.max(1, end - size + 1);
    }

    for (let i = start; i <= end; i++) {
      pages.push(
        <li
          key={i}
          className={`page-item ${i === currentPage ? "active" : ""}`}
        >
          <button className="page-link" onClick={() => setCurrentPage(i)}>
            {i}
          </button>
        </li>
      );
    }
    return pages;
  };

  return (
    <nav aria-label="Page navigation">
      <ul className="pagination justify-content-center">
        <li className={`page-item ${currentPage === 1 ? "disabled" : ""}`}>
          <button
            className="page-link"
            onClick={onFirstPage}
            disabled={currentPage === 1}
          >
            {"<<"}
          </button>
        </li>
        <li className={`page-item ${currentPage === 1 ? "disabled" : ""}`}>
          <button
            className="page-link"
            onClick={onPrevPage}
            disabled={currentPage === 1}
          >
            {"<"}
          </button>
        </li>
        {renderPageNumbers()}
        <li
          className={`page-item ${
            currentPage === totalPages ? "disabled" : ""
          }`}
        >
          <button
            className="page-link"
            onClick={onNextPage}
            disabled={currentPage === totalPages}
          >
            {">"}
          </button>
        </li>
        <li
          className={`page-item ${
            currentPage === totalPages ? "disabled" : ""
          }`}
        >
          <button
            className="page-link"
            onClick={onLastPage}
            disabled={currentPage === totalPages}
          >
            {">>"}
          </button>
        </li>
      </ul>
    </nav>
  );
};

export default Pagination;
