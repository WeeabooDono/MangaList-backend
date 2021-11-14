package fr.weeab.mangalist.webapp.exception;

import fr.weeab.mangalist.core.exception.BadRequestException;
import fr.weeab.mangalist.core.exception.NotFoundException;
import fr.weeab.mangalist.core.exception.tools.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * Handle all BadRequestException
     *
     * @param ex      a BadRequestException
     * @param request the web request
     * @return an exception response that contains the detail
     * of the BadRequestException
     */
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(final BadRequestException ex, final WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex,
                new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle all NotFoundException
     *
     * @param ex      a NotFoundException
     * @param request the web request
     * @return an exception response that contains the detail
     * of the NotFoundException
     */
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex, final WebRequest request) {
        LOG.error(ex.getMessage(), ex);
        return this.handleExceptionInternal(ex,
                new ExceptionResponse(ex.getTitleKey(), ex.getMessageKey(), ex),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    /**
     * Handle a ConstraintViolationException for @Validation
     *
     * @param ex      a ConstraintViolationException
     * @param request the web request
     * @return an exception response that contains the detail
     * of the ConstraintViolationException
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {
        BadRequestException badRequestException = new BadRequestException(
                BadRequestException.BadRequestExceptionType.PARAMETER_BAD_VALUE,
                ex.getMessage(),
                ex);

        LOG.error(badRequestException.getMessage(), badRequestException);

        return this.handleExceptionInternal(ex,
                new ExceptionResponse(badRequestException.getTitleKey(), badRequestException.getMessage(), badRequestException),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    /**
     * Customize the response for MissingServletRequestParameterException.
     * This method delegates to {@link #handleExceptionInternal}.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BadRequestException badRequestException = new BadRequestException(
                BadRequestException.BadRequestExceptionType.PARAMETER_MISSING,
                ex.getMessage(),
                ex,
                ex.getParameterName());

        LOG.error(badRequestException.getMessage(), badRequestException);

        return this.handleExceptionInternal(ex,
                new ExceptionResponse(badRequestException.getTitleKey(), badRequestException.getMessage(), badRequestException),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    /**
     * Customize the response for MethodArgumentNotValidException.
     * This method delegates to {@link #handleExceptionInternal}.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        BadRequestException badRequestException = new BadRequestException(
                BadRequestException.BadRequestExceptionType.PARAMETER_BAD_TYPE,
                ex.getMessage(),
                ex,
                ex.getParameter().getParameterName());

        LOG.error(badRequestException.getMessage(), badRequestException);

        return this.handleExceptionInternal(ex,
                new ExceptionResponse(badRequestException.getTitleKey(), badRequestException.getMessage(), badRequestException),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

}
