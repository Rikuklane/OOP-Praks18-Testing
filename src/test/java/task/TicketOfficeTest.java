package task;

import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TicketOfficeTest {

  /* test template
  @Test
  public void testSomething() {
    TicketDatabase db = mock(TicketDatabase.class);
    TicketOffice office = new TicketOffice(db);

    // prepare the mock (when+thenReturn)
    // call office.buyTicket
    // assert/verify conditions
  }
  */

    @Test // expected = IllegalStateException.class
    public void throwsExceptionWhenNoSeatsAvailable() {
        TicketDatabase db = mock(TicketDatabase.class);
        TicketOffice office = new TicketOffice(db);

        when(db.getFreeSeats(anyString())).thenReturn(Collections.emptyList());
        office.buyTicket("any");
    }

    @Test
    public void returnsAvailableSeatOnPurchase() {
        TicketDatabase db = mock(TicketDatabase.class);
        TicketOffice office = new TicketOffice(db);

        List<Integer> availableSeats = Arrays.asList(1,3,5,7,9);
        when(db.getFreeSeats(anyString())).thenReturn(availableSeats);
        int seat = office.buyTicket("show");
        assertTrue(availableSeats.contains(seat));
    }

    @Test
    public void callsReserveSeatWithCorrectArguments() {
        TicketDatabase db = mock(TicketDatabase.class);
        TicketOffice office = new TicketOffice(db);

        when(db.getFreeSeats(anyString())).thenReturn(singletonList(42));
        office.buyTicket("show");

        verify(db, times(1)).reserveSeat("show", 42);
    }

}
