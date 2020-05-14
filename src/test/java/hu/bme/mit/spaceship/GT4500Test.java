package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primmock;
  private TorpedoStore secomock;


  @BeforeEach
  public void init(){
    primmock = mock(TorpedoStore.class);
    secomock = mock(TorpedoStore.class);
    this.ship = new GT4500();
    this.ship.fireaction(primmock, secomock, false);
    

  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
   
    
    when(primmock.fire(1)).thenReturn(true);
    when(secomock.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primmock, times(1)).fire(1);
    verify(secomock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primmock.fire(1)).thenReturn(true);
    when(secomock.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primmock, times(1)).fire(1);
    verify(secomock, times(1)).fire(1);
  }

  public void fireTorpedo_Single_Failure(){
    // Arrange
   
    
    when(primmock.fire(1)).thenReturn(false);
    when(secomock.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    //assertEquals(true, result);
    assertEquals(false, result);
    verify(primmock, times(1)).fire(1);
    verify(secomock, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure(){
    // Arrange
    when(primmock.fire(1)).thenReturn(false);
    when(secomock.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    //assertEquals(true, result);
    assertEquals(false, result);
    verify(primmock, times(1)).fire(1);
    verify(secomock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_primary_Empty(){
    // Arrange
    when(primmock.isEmpty()).thenReturn(false);
    when(secomock.fire(1)).thenReturn(true);
    // Act
    boolean resultpri = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultsec = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(false, resultpri);
    assertEquals(true, resultsec);
    verify(primmock, times(1)).isEmpty();
    verify(secomock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_second_Empty(){
    // Arrange
    when(primmock.fire(1)).thenReturn(true);
    when(secomock.isEmpty()).thenReturn(false);
    // Act
    boolean resultpri = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultsec = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(true, resultpri);
    assertEquals(false, resultsec);
    verify(primmock, times(1)).fire(1);
    verify(secomock, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_both_Empty(){
    // Arrange
    when(primmock.isEmpty()).thenReturn(true);
    when(secomock.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    // Assert
    assertEquals(false, result);
    verify(primmock, times(0)).fire(1);
    verify(secomock, times(0)).fire(1);
  }




}
